package com.example.cloneui_socialmedia

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import android.graphics.LinearGradient
import android.graphics.Shader
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragmentActivity : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Make gradient title
        val appTitle = view.findViewById<TextView>(R.id.home_title)
        val titleWidth = appTitle.paint.measureText(appTitle.text.toString())
        val titleShader = LinearGradient(
            0f,0f,titleWidth,appTitle.textSize,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.homeGradient1),
                ContextCompat.getColor(requireContext(), R.color.homeGradient2),
            ),
            null,
            Shader.TileMode.CLAMP
        )
        appTitle.paint.shader = titleShader

        //Story RecyclerView
        val storyRecyclerView = view.findViewById<RecyclerView>(R.id.home_storyBar)
        val storyList = mutableListOf(
            StoryData("https://miro.medium.com/v2/resize:fit:920/1*R5zt6Upx-ba905xLU6HNXw.gif", "Your Story", 0),
            StoryData("https://scontent.fbkk5-4.fna.fbcdn.net/v/t39.30808-1/468165860_3914841105498819_1916566480743387954_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=103&ccb=1-7&_nc_sid=e99d92&_nc_ohc=36cbhpWMqIUQ7kNvwFGIBR-&_nc_oc=AdkORAYPRJ8c3iJ-mzGj0seVhAIW3EyOFPQA-KojAT4wsyruEKJeeGDLcbOKkZn63hA&_nc_zt=24&_nc_ht=scontent.fbkk5-4.fna&_nc_gid=ET6D-ZsimPi2AFbB1RhW5g&oh=00_AfFJlUqwYXrl97kZc4sr6_GCX4b79VOZFWsBqEa-LN5jxA&oe=681A5790", "aolqn_", 0),
            StoryData("https://scontent.fbkk5-4.fna.fbcdn.net/v/t39.30808-1/419869161_2107802932945533_3763333289885930527_n.jpg?stp=cp6_dst-jpg_s200x200_tt6&_nc_cat=110&ccb=1-7&_nc_sid=e99d92&_nc_ohc=pBje0ATjZ2AQ7kNvwEWMbsN&_nc_oc=Adlpc91w4nE5cBpA866nvf0bk5rOStCEy8JiY0prn-1v5rbn5VNsmk4ResrX7KWFKYQ&_nc_zt=24&_nc_ht=scontent.fbkk5-4.fna&_nc_gid=Z5lSvy52XlIH2DRqium97g&oh=00_AfFqvtdblbLTNaE2KZKVw2kSKzhC-AyD3QKs-ax6TmYf7w&oe=681A4DC6", "mello_mrch", 0),
            StoryData("https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.6435-1/125317852_1006701349810671_7985413238749674312_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=102&ccb=1-7&_nc_sid=e99d92&_nc_ohc=CooVS-2dijAQ7kNvwFAW_fO&_nc_oc=AdnEkhU3NlJqri9f6wYsqO41VCTvlXkv1j09HKIJJ4r8PdJlzuD84xNj8Cdx0j6KSX0&_nc_zt=24&_nc_ht=scontent.fbkk5-6.fna&_nc_gid=A20DLHbW4Pci2_4jmokDuA&oh=00_AfH5sz9qX08J1TAu-FWxtxx52t7o1nWcY3VvDL2Jw3m6kg&oe=683BED00", "pk_pnr_ag", 0),
            StoryData("https://scontent.fbkk5-5.fna.fbcdn.net/v/t39.30808-1/458209237_391838393968088_5727269422900251919_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=104&ccb=1-7&_nc_sid=e99d92&_nc_ohc=1wQzbG15VpoQ7kNvwFyoCa3&_nc_oc=AdnoVQH9zBmF81exY2bx3MS1wmPJSEAG7LehFDovrN_Jrp3m1HS_9O4_vy-jUwafd-c&_nc_zt=24&_nc_ht=scontent.fbkk5-5.fna&_nc_gid=PGQdsQHIn1fNIDTxe3xrWw&oh=00_AfF29CL0gCLDikcqJxE2zDSJlgkrMyd8fXUNcewLNbDYrQ&oe=681A313C", "tawanrachata", 0),
            StoryData("https://scontent.fbkk5-7.fna.fbcdn.net/v/t1.6435-1/122451609_3402745903154072_6488388744948489711_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=108&ccb=1-7&_nc_sid=e99d92&_nc_ohc=Dc11MbELcxkQ7kNvwFoGWut&_nc_oc=AdlwA8L1TSdnWnUAk7N6UK3t3uH2O2LTtujaIeAhDIjO8eNHXpzS66osqhMBHMb0Q94&_nc_zt=24&_nc_ht=scontent.fbkk5-7.fna&_nc_gid=ScawA9DkWaPp0GR-QxHIcA&oh=00_AfFyaqg7RHmVbqylopN_0xkFzo9mdX9y8oG_1pg1KACZ4w&oe=683C00C7", "m.jtpc", 0),
            StoryData("https://scontent.fbkk5-6.fna.fbcdn.net/v/t1.6435-1/79158997_694657317728457_1265777203442876416_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=101&ccb=1-7&_nc_sid=e99d92&_nc_ohc=ZN49gIdPxG0Q7kNvwGiQOj7&_nc_oc=Adlb_D9ktCuEu3GZdrF7g7dsyrmV1T6Q4ZTqKDhATX1bJzsYls-hxGCpgJh6drHy1-Q&_nc_zt=24&_nc_ht=scontent.fbkk5-6.fna&_nc_gid=Z170rSJodW-MyZ2nO6y0Ug&oh=00_AfGAArf4S4Y63vktnS_Y8b3VlwrPiCZFGPOWKGDHLF_v4g&oe=683BF100", "dfrostg", 0),
        )
        val storyAdapter = StoryAdapter(storyList)
        storyRecyclerView.adapter = storyAdapter
        storyRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

    }
}