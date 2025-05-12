package com.example.cloneui_socialmedia.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cloneui_socialmedia.ExploreItem
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.adapters.ExploreAdapter
import com.example.cloneui_socialmedia.adapters.PostAdapter
import com.example.cloneui_socialmedia.models.PostData

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    //Mock Data for Posts
    private var mockMutableList : MutableList<PostData> = mutableListOf(
        PostData(
            "Flutter Official",
            "flutterdev",
            "https://cdn-images-1.medium.com/max/1200/1*5-aoK8IBmXve5whBQM90GA.png",
            "2025-02-13",
            "https://miro.medium.com/v2/resize:fit:3840/format:webp/1*-XHpdhmBoEU-K3pSPWTX3g.png",
            "Dive into Flutter 3.29! This release refines development and boosts performance, with updates to Impeller, Cupertino, DevTools and more. With 104 unique authors contributing this release cycle, Flutter 3.29 showcases the community’s dedication. Let’s explore what’s new! #flutter ",
            0,
            false,
            0,
            0,
            0,
            false,
            isRecommended = true
        ),
        PostData("Major Group",
            "majorcineplex",
            "https://scontent.fbkk14-1.fna.fbcdn.net/v/t39.30808-6/492548952_1133013508855724_70950623530622578_n.jpg?_nc_cat=1&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=bsw3XYm76vEQ7kNvwGtFIfU&_nc_oc=AdmGKf6ulbb44CkY2-ka6J7jEzwGifKfjnjzLgxeQ6N27XtAfnUTPJFIVl1hOgYZRhY&_nc_zt=23&_nc_ht=scontent.fbkk14-1.fna&_nc_gid=0_iid83fTRX8kWdVnuEdBg&oh=00_AfKB3J2tgi1LDCJ-E7vPf3qisi5HjiV1zbOP5h97XmpDWQ&oe=681F5318",
            "2025-03-29",
            "https://scontent.fbkk10-1.fna.fbcdn.net/v/t39.30808-6/492233042_1130141512476257_4684539077288037862_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=127cfc&_nc_ohc=ymgOphIECdIQ7kNvwGU1OjM&_nc_oc=Adl4a2AqcywfR64Gc4hOm37ksuznln3dpb5GqNQUVkd1Tt5qrxr6OP6Dt5T8Ad0FYfY&_nc_zt=23&_nc_ht=scontent.fbkk10-1.fna&_nc_gid=iRJZlkF6jC34C3_4Ik1_Jw&oh=00_AfLjQz7n8P0G7Er5UAwW6XEcPGv0-G4BtuiEpYwyL4cgyg&oe=681F341C",
            "จากเกมชื่อดังสู่ภาพยนตร์เหนือจินตนาการ A #Minecraft Movie - ไมน์คราฟต์ มูฟวี่ เข้าฉาย 3 เมษายนนี้ที่ เมเจอร์ ซีนีเพล็กซ์ \n #MinecraftMovie",
            0, false, 0, 0, 0, false, isTrending = true),
        PostData(
            "Thailand Game Show",
            "tgs",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLi16IJRN_HwEwADqGWBdmrRJTUdUfDFYvkw&s",
            "2025-02-20", "https://www.thailandgameshow.com/wp-content/uploads/2025/02/GAxTGS-2025-Banner-1640px-x-720px.png",
            "Thailand Game Show ปีนี้จะยิ่งใหญ่กว่าเดิม เมื่อ gamescom asia งานเกมสุดยิ่งใหญ่ในระดับภูมิภาคเอเชีย ย้ายฐานการจัดงานจากสิงคโปร์มาจัดในไทยเป็นครั้งแรกร่วมกับ TGS เกิดเป็น gamescom asia x Thailand Game Show ระหว่างวันที่ 16 - 19 ตุลาคมนี้ ที่ศูนย์การประชุมแห่งชาติสิริกิติ์ ! #thailandgameshow",
            0, false, 0, 0, 0, false, isRecommended = true, isTrending = true),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.explore_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }



    private fun setupRecyclerView() {
        val trendingPosts = mockMutableList.filter { it.isTrending }
        val recommendedPosts = mockMutableList.filter { it.isRecommended }

        val exploreViews: MutableList<ExploreItem> = mutableListOf()

        if(trendingPosts.isEmpty() && recommendedPosts.isEmpty()) {
            exploreViews.add(ExploreItem.EmptyState)
        }else{
            exploreViews.add(ExploreItem.RecommendTitle)
            if (recommendedPosts.isNotEmpty()) {
                recommendedPosts.forEach { exploreViews.add(ExploreItem.RecommendPost(it)) }
                exploreViews.add(ExploreItem.RelevantBox)
            }else{
                exploreViews.add(ExploreItem.EmptyState)
            }
            exploreViews.add(ExploreItem.TrendingTitle)
            if (trendingPosts.isNotEmpty()) {
                trendingPosts.forEach { exploreViews.add(ExploreItem.TrendingPost(it)) }
            }else{
                exploreViews.add(ExploreItem.EmptyState)
            }
        }



        val exploreRecyclerView = view?.findViewById<RecyclerView>(R.id.explore_contents)
        val exploreAdapter = ExploreAdapter()
        exploreRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        exploreRecyclerView?.adapter = exploreAdapter
        exploreAdapter.submitList(exploreViews)
    }

}