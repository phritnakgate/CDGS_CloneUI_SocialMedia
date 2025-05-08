package com.example.cloneui_socialmedia.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.cloneui_socialmedia.R

class AddFragment : Fragment(R.layout.fragment_add) {

    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var cameraSelector: CameraSelector
    private lateinit var preview: Preview
    private lateinit var previewView: PreviewView

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startCamera()
            } else {
                // Optionally notify user permission was denied
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        previewView = view.findViewById(R.id.preview_view_camera)
        cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
        preview = Preview.Builder().build()

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                startCamera()
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindPreview()
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun bindPreview() {
        cameraProvider.unbindAll()
        preview.surfaceProvider = previewView.surfaceProvider

        cameraProvider.bindToLifecycle(
            viewLifecycleOwner,
            cameraSelector,
            preview
        )
    }
}
