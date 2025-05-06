package com.example.cloneui_socialmedia.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.PostData

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var trendingRecyclerView : androidx.recyclerview.widget.RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trendingPostInit()

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.explore_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }
    //Trending
    private fun trendingPostInit(){
        val trendingList = mutableListOf(
            PostData("John Doe", "johnDoe123", "https://miro.medium.com/v2/resize:fit:920/1*R5zt6Upx-ba905xLU6HNXw.gif", "2025-05-01", "https://shared.fastly.steamstatic.com/store_item_assets/steam/apps/2456740/header.jpg?t=1744688805", "อยากเล่นเกมนี้มากๆ inZOI พัฒนาโดย inZOI Studio และจัดจำหน่ายโดยคราฟตันเกมนี้จะวางจำหน่ายในวันที่ 28 มีนาคม ค.ศ. 2025", 0, false, 0, 0, 0, false),
            PostData("Major Group", "majorcineplex", "https://scontent.fbkk14-1.fna.fbcdn.net/v/t39.30808-6/492548952_1133013508855724_70950623530622578_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=bsw3XYm76vEQ7kNvwGtFIfU&_nc_oc=AdmGKf6ulbb44CkY2-ka6J7jEzwGifKfjnjzLgxeQ6N27XtAfnUTPJFIVl1hOgYZRhY&_nc_zt=23&_nc_ht=scontent.fbkk14-1.fna&_nc_gid=0_iid83fTRX8kWdVnuEdBg&oh=00_AfKB3J2tgi1LDCJ-E7vPf3qisi5HjiV1zbOP5h97XmpDWQ&oe=681F5318", "2025-03-29", "https://scontent.fbkk10-1.fna.fbcdn.net/v/t39.30808-6/492233042_1130141512476257_4684539077288037862_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=ymgOphIECdIQ7kNvwGU1OjM&_nc_oc=Adl4a2AqcywfR64Gc4hOm37ksuznln3dpb5GqNQUVkd1Tt5qrxr6OP6Dt5T8Ad0FYfY&_nc_zt=23&_nc_ht=scontent.fbkk10-1.fna&_nc_gid=iRJZlkF6jC34C3_4Ik1_Jw&oh=00_AfLjQz7n8P0G7Er5UAwW6XEcPGv0-G4BtuiEpYwyL4cgyg&oe=681F341C", "จากเกมชื่อดังสู่ภาพยนตร์เหนือจินตนาการ A #Minecraft Movie - ไมน์คราฟต์ มูฟวี่ เข้าฉาย 3 เมษายนนี้ที่ เมเจอร์ ซีนีเพล็กซ์ \n #MinecraftMovie", 0, false, 0, 0, 0, false),
            PostData("Thailand Game Show", "tgs", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLi16IJRN_HwEwADqGWBdmrRJTUdUfDFYvkw&s", "2025-02-20", "https://www.thailandgameshow.com/wp-content/uploads/2025/02/GAxTGS-2025-Banner-1640px-x-720px.png", "Thailand Game Show ปีนี้จะยิ่งใหญ่กว่าเดิม เมื่อ gamescom asia งานเกมสุดยิ่งใหญ่ในระดับภูมิภาคเอเชีย ย้ายฐานการจัดงานจากสิงคโปร์มาจัดในไทยเป็นครั้งแรกร่วมกับ TGS เกิดเป็น gamescom asia x Thailand Game Show ระหว่างวันที่ 16 - 19 ตุลาคมนี้ ที่ศูนย์การประชุมแห่งชาติสิริกิติ์ ! #thailandgameshow", 0, false, 0, 0, 0, false),
        )
        val trendingPostAdapter = com.example.cloneui_socialmedia.adapters.PostAdapter(trendingList)
        trendingRecyclerView = view?.findViewById(R.id.explore_trendingPost)!!
        trendingRecyclerView.adapter = trendingPostAdapter
        trendingRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        trendingRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }
}