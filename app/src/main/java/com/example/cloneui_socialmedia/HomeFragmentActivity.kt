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
import androidx.recyclerview.widget.DividerItemDecoration
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

        //Post RecyclerView
        val postRecyclerView = view.findViewById<RecyclerView>(R.id.home_posts)
        val postList = mutableListOf(
            PostData("John Doe", "johnDoe123", "https://miro.medium.com/v2/resize:fit:920/1*R5zt6Upx-ba905xLU6HNXw.gif", "2025-05-01", "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2456740/header.jpg?t=1744688805", "อยากเล่นเกมนี้มากๆ inZOI พัฒนาโดย inZOI Studio และจัดจำหน่ายโดยคราฟตันเกมนี้จะวางจำหน่ายในวันที่ 28 มีนาคม ค.ศ. 2025", 0, false, 0, 0, 0, false),
            PostData("Major Group", "majorcineplex", "https://scontent.fbkk14-1.fna.fbcdn.net/v/t39.30808-6/492548952_1133013508855724_70950623530622578_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=bsw3XYm76vEQ7kNvwGtFIfU&_nc_oc=AdmGKf6ulbb44CkY2-ka6J7jEzwGifKfjnjzLgxeQ6N27XtAfnUTPJFIVl1hOgYZRhY&_nc_zt=23&_nc_ht=scontent.fbkk14-1.fna&_nc_gid=0_iid83fTRX8kWdVnuEdBg&oh=00_AfKB3J2tgi1LDCJ-E7vPf3qisi5HjiV1zbOP5h97XmpDWQ&oe=681F5318", "2025-03-29", "https://scontent.fbkk10-1.fna.fbcdn.net/v/t39.30808-6/492233042_1130141512476257_4684539077288037862_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=ymgOphIECdIQ7kNvwGU1OjM&_nc_oc=Adl4a2AqcywfR64Gc4hOm37ksuznln3dpb5GqNQUVkd1Tt5qrxr6OP6Dt5T8Ad0FYfY&_nc_zt=23&_nc_ht=scontent.fbkk10-1.fna&_nc_gid=iRJZlkF6jC34C3_4Ik1_Jw&oh=00_AfLjQz7n8P0G7Er5UAwW6XEcPGv0-G4BtuiEpYwyL4cgyg&oe=681F341C", "จากเกมชื่อดังสู่ภาพยนตร์เหนือจินตนาการ A #Minecraft Movie - ไมน์คราฟต์ มูฟวี่ เข้าฉาย 3 เมษายนนี้ที่ เมเจอร์ ซีนีเพล็กซ์ \n #MinecraftMovie", 0, false, 0, 0, 0, false),
            PostData("Thailand Game Show", "tgs", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLi16IJRN_HwEwADqGWBdmrRJTUdUfDFYvkw&s", "2025-02-20", "https://www.thailandgameshow.com/wp-content/uploads/2025/02/GAxTGS-2025-Banner-1640px-x-720px.png", "Thailand Game Show ปีนี้จะยิ่งใหญ่กว่าเดิม เมื่อ gamescom asia งานเกมสุดยิ่งใหญ่ในระดับภูมิภาคเอเชีย ย้ายฐานการจัดงานจากสิงคโปร์มาจัดในไทยเป็นครั้งแรกร่วมกับ TGS เกิดเป็น gamescom asia x Thailand Game Show ระหว่างวันที่ 16 - 19 ตุลาคมนี้ ที่ศูนย์การประชุมแห่งชาติสิริกิติ์ ! #thailandgameshow", 0, false, 0, 0, 0, false),
        )
        val postAdapter = PostAdapter(postList)
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        postRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.home_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

    }
}