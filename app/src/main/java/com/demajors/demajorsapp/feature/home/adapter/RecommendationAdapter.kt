package com.demajors.demajorsapp.feature.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemRecommendationBinding
import com.demajors.demajorsapp.feature.song.DetailSongActivity
import com.demajors.demajorsapp.model.home.HomeItem

class RecommendationAdapter(val data: MutableList<HomeItem>) : RecyclerView.Adapter<RecommendationAdapter.RecommendationHolder>() {

    class RecommendationHolder(val binding: ViewItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder =
        RecommendationHolder(
            ViewItemRecommendationBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        DetailSongActivity::class.java
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
