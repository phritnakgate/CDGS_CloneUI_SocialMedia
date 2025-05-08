package com.example.cloneui_socialmedia.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cloneui_socialmedia.ExploreDiffCallback
import com.example.cloneui_socialmedia.ExploreItem
import com.example.cloneui_socialmedia.ExploreType
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.PostData
import com.example.cloneui_socialmedia.utils.PostBinder

class ExploreAdapter : ListAdapter<ExploreItem, RecyclerView.ViewHolder>(ExploreDiffCallback) {


    //ViewHolder
    inner class EmptyStateViewHolder(view: View) : RecyclerView.ViewHolder(view)
    inner class RecommendTitleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    inner class TrendingTitleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    inner class RelevantBoxViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val relavantLikeBtn = view.findViewById<AppCompatImageView>(R.id.explore_relevantLike)
        private val relavantUnlikeBtn = view.findViewById<AppCompatImageView>(R.id.explore_relevantUnLike)
        private var isLike : Boolean = false
        private var isUnlike : Boolean = false

        fun bind() {
            relavantLikeBtn.setOnClickListener {
                if (!isLike) {
                    // Like is selected
                    isLike = true
                    isUnlike = false
                    relavantLikeBtn.setImageResource(R.drawable.explore_like_filled)
                    relavantUnlikeBtn.setImageResource(R.drawable.explore_unlike)
                } else {
                    // Like is unselected
                    isLike = false
                    relavantLikeBtn.setImageResource(R.drawable.explore_like)
                }
                Log.d("LIKE | UNLIKE", "Like: $isLike | Unlike: $isUnlike")
            }

            relavantUnlikeBtn.setOnClickListener {
                if (!isUnlike) {
                    // Unlike is selected
                    isUnlike = true
                    isLike = false
                    relavantUnlikeBtn.setImageResource(R.drawable.explore_unlike_filled)
                    relavantLikeBtn.setImageResource(R.drawable.explore_like)
                } else {
                    // Unlike is unselected
                    isUnlike = false
                    relavantUnlikeBtn.setImageResource(R.drawable.explore_unlike)
                }
                Log.d("LIKE | UNLIKE", "Like: $isLike | Unlike: $isUnlike")
            }
        }
    }

    inner class RecommendPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val recommendedContainer = view.findViewById<FrameLayout>(R.id.framelayout_explore_recommendPosts)

        fun bind(post: PostData) {
            recommendedContainer.removeAllViews()
            val postView = LayoutInflater.from(recommendedContainer.context).inflate(R.layout.recycler_post, recommendedContainer, false)
            PostBinder.bind(postView,post)
            recommendedContainer.addView(postView)
        }
    }
    inner class TrendingPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val trendingContainer = view.findViewById<FrameLayout>(R.id.framelayout_explore_trendingPosts)

        fun bind(post: PostData) {
            trendingContainer.removeAllViews()
            val postView = LayoutInflater.from(trendingContainer.context).inflate(R.layout.recycler_post, trendingContainer, false)
            PostBinder.bind(postView,post)
            trendingContainer.addView(postView)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ExploreItem.RecommendTitle -> ExploreType.TYPE_RECOMMEND_TITLE
            is ExploreItem.RecommendPost -> ExploreType.TYPE_RECOMMEND_POST
            is ExploreItem.RelevantBox -> ExploreType.TYPE_APPEAL_OR_NOT
            is ExploreItem.TrendingTitle -> ExploreType.TYPE_TRENDING_TITLE
            is ExploreItem.TrendingPost -> ExploreType.TYPE_TRENDING_POST
            is ExploreItem.EmptyState -> ExploreType.TYPE_EMPTY_STATE
            else -> ExploreType.TYPE_EMPTY_STATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ExploreType.TYPE_RECOMMEND_TITLE -> {
                val view = inflater.inflate(R.layout.explore_recommendtitle, parent, false)
                RecommendTitleViewHolder(view)
            }
            ExploreType.TYPE_RECOMMEND_POST -> {
                val view = inflater.inflate(R.layout.explore_recommendedpost, parent, false)
                RecommendPostViewHolder(view)
            }
            ExploreType.TYPE_APPEAL_OR_NOT -> {
                val view = inflater.inflate(R.layout.explore_relevant, parent, false)
                RelevantBoxViewHolder(view)
            }
            ExploreType.TYPE_TRENDING_TITLE -> {
                val view = inflater.inflate(R.layout.explore_trendingtitle, parent, false)
                TrendingTitleViewHolder(view)
            }
            ExploreType.TYPE_TRENDING_POST -> {
                val view = inflater.inflate(R.layout.explore_trendingpost, parent, false)
                TrendingPostViewHolder(view)
            }
            ExploreType.TYPE_EMPTY_STATE -> {
                val view = inflater.inflate(R.layout.explore_emptystate, parent, false)
                EmptyStateViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is RecommendPostViewHolder && item is ExploreItem.RecommendPost -> holder.bind(item.post)
            holder is RelevantBoxViewHolder && item is ExploreItem.RelevantBox -> holder.bind()
            holder is TrendingPostViewHolder && item is ExploreItem.TrendingPost -> holder.bind(item.post)
        }
    }

}
