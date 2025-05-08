package com.example.cloneui_socialmedia.models

data class PostData (
    val username : String,
    val userId : String,
    val profileImgUrl : String,
    val datePosted : String,
    val postImage : String,
    val postDescription : String,
    var postLikes : Int,
    var isPostLiked : Boolean,
    val postComments : Int,
    val postShares : Int,
    var postBookmark : Int,
    var isPostBookmarked : Boolean,
    var isRecommended : Boolean = false,
    var isTrending : Boolean = false
)