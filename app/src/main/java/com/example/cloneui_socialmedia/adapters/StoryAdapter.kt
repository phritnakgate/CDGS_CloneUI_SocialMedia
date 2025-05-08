package com.example.cloneui_socialmedia.adapters

import android.animation.Animator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.cloneui_socialmedia.R
import com.example.cloneui_socialmedia.models.StoryData

class StoryAdapter(private var itemLists : MutableList<StoryData>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val glide : RequestManager = Glide.with(itemView.context)
        val storyImg : AppCompatImageView? = itemView.findViewById(R.id.home_storyImg)
        val storyAnimation : LottieAnimationView = itemView.findViewById(R.id.home_storyAnimation)
        val username : TextView? = itemView.findViewById(R.id.home_storyUsername)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_story,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemLists[position]
        holder.username?.text = item.username

        // Handle Profile Image
        holder.storyImg
        holder.glide.load(item.profileImgUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.storyImg!!)

        // Handle Lottie Animation
        holder.storyAnimation.removeAllAnimatorListeners()
        when(item.timeClicked) {
           0 -> {
                holder.storyAnimation.setAnimation(R.raw.story_unseen_animation)
                holder.storyAnimation.progress = 1f
            }
            1 -> {
                holder.storyAnimation.setAnimation(R.raw.story_seen_animation)
                holder.storyAnimation.progress = 1f
            }
           else -> {
               holder.storyAnimation.setAnimation(R.raw.story_seen_animation)
               holder.storyAnimation.progress = 1f
           }
        }
        holder.storyAnimation.setOnClickListener{
            if(item.timeClicked == 0){
                holder.storyAnimation.setAnimation(R.raw.story_unseen_animation)
                holder.storyAnimation.playAnimation()
                holder.storyAnimation.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(animation: Animator) {}
                    override fun onAnimationEnd(animation: Animator) {
                        item.timeClicked = 1
                        holder.storyAnimation.setAnimation(R.raw.story_seen_animation)
                    }
                    override fun onAnimationCancel(animation: Animator) {}
                    override fun onAnimationRepeat(animation: Animator) {}})
            }else{
                    holder.storyAnimation.setAnimation(R.raw.story_seen_animation)
                    holder.storyAnimation.playAnimation()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemLists.size
    }
}