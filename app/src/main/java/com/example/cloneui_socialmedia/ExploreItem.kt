package com.example.cloneui_socialmedia

import com.example.cloneui_socialmedia.models.PostData


/**
เพื่อให้ข้อมูลที่ส่งให้กับ ViewHolder แต่ละตัวสามารถแยกกันได้ง่าย ควรสร้าง Sealed Class ขึ้นมาเพื่อเก็บประเภทของข้อมูลแต่ละตัวว่าเป็นข้อมูลประเภทไหน
 **/
sealed class ExploreItem {
    data object RecommendTitle : ExploreItem()
    data class RecommendPost(val posts: List<PostData>) : ExploreItem()
    data object RelevantBox : ExploreItem()
    data object TrendingTitle : ExploreItem()
    data class TrendingPost(val posts: List<PostData>) : ExploreItem()
}