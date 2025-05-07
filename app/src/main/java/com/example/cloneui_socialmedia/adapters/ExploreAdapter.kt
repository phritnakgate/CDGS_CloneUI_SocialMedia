package com.example.cloneui_socialmedia.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cloneui_socialmedia.ExploreDiffCallback
import com.example.cloneui_socialmedia.ExploreItem
import com.example.cloneui_socialmedia.ExploreType
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.PostData

class ExploreAdapter : ListAdapter<ExploreItem, RecyclerView.ViewHolder>(ExploreDiffCallback) {

    //ViewHolder
    class RecommendTitleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class TrendingTitleViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class RelevantBoxViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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

    class RecommendPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rv = view.findViewById<RecyclerView>(R.id.explore_recommendPost)
        fun bind(posts: List<PostData>) {
            val adapter = PostAdapter(posts.toMutableList())
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)

        }
    }
    class TrendingPostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rv = view.findViewById<RecyclerView>(R.id.explore_trendingPost)
        fun bind(posts: List<PostData>) {
            val adapter = PostAdapter(posts.toMutableList())
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ExploreItem.RecommendTitle -> ExploreType.TYPE_RECOMMEND_TITLE
            is ExploreItem.RecommendPost -> ExploreType.TYPE_RECOMMEND_POST
            is ExploreItem.RelevantBox -> ExploreType.TYPE_APPEAL_OR_NOT
            is ExploreItem.TrendingTitle -> ExploreType.TYPE_TRENDING_TITLE
            is ExploreItem.TrendingPost -> ExploreType.TYPE_TRENDING_POST
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
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when {
            holder is RecommendPostViewHolder && item is ExploreItem.RecommendPost -> holder.bind(item.posts)
            holder is RelevantBoxViewHolder && item is ExploreItem.RelevantBox -> holder.bind()
            holder is TrendingPostViewHolder && item is ExploreItem.TrendingPost -> holder.bind(item.posts)
        }
    }

}
