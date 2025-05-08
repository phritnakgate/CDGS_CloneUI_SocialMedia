package com.example.cloneui_socialmedia.fragments

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.utils.Constants
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddFragment : Fragment(R.layout.fragment_add) {
    // https://developer.android.com/codelabs/camerax-getting-started#0 \\
    //Init Views
    private lateinit var cameraView: PreviewView
    private lateinit var switchCameraBtn : ConstraintLayout
    private lateinit var cameraCaptureBtn : ConstraintLayout
    private lateinit var openGalleryBtn : ConstraintLayout

    //CameraX
    private lateinit var cameraExecutor : ExecutorService
    private var imageCapture : ImageCapture? = null
    private var cameraFacing: Int = CameraSelector.LENS_FACING_BACK


    //Manage Camera and Gallery Permission
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Permission denied :(", Toast.LENGTH_SHORT).show()
            }
        }
    private val openGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri: Uri? = result.data?.data
                selectedImageUri?.let {
                    Toast.makeText(requireContext(), "Gallery Image Selected: $it", Toast.LENGTH_SHORT).show()
                }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
        cameraExecutor = Executors.newSingleThreadExecutor()

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }

    private fun initViews(){
        cameraView = requireView().findViewById(R.id.previewview_camera_view)
        switchCameraBtn = requireView().findViewById(R.id.constraintlayout_camera_swichcam)
        cameraCaptureBtn = requireView().findViewById(R.id.constraintlayout_camera_capture)
        openGalleryBtn = requireView().findViewById(R.id.constraintlayout_camera_gallery)
    }

    private fun initListeners(){
        switchCameraBtn.setOnClickListener {switchCamera()}
        cameraCaptureBtn.setOnClickListener {takePhoto()}
        openGalleryBtn.setOnClickListener {openGallery()}
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.surfaceProvider = cameraView.surfaceProvider
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(cameraFacing)
                .build()

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (exc: Exception) {
                Log.e("AddFragment", "Camera binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val name = SimpleDateFormat(Constants.ISO_DATE_FORMAT, Locale.US)
            .format(System.currentTimeMillis())

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "DCIM/Camera")
        }

        val contentResolver = requireActivity().contentResolver
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (uri == null) {
            Log.e("AddFragment", "Failed to create MediaStore entry.")
            return
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(contentResolver, uri, null).build()

        imageCapture.takePicture(
            outputOptions,
            cameraExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e("AddFragment", "Photo capture failed: ${exception.message}", exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    requireActivity().runOnUiThread {
                        Toast.makeText(requireContext(), "Photo saved to gallery!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }


    private fun switchCamera(){
        cameraFacing = if (cameraFacing == CameraSelector.LENS_FACING_BACK) {
            CameraSelector.LENS_FACING_FRONT
        } else {
            CameraSelector.LENS_FACING_BACK
        }
        startCamera()
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        openGalleryLauncher.launch(intent)
    }


}
