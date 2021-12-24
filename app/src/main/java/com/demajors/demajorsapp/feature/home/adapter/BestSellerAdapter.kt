package com.demajors.demajorsapp.feature.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemBestSellerBinding
import com.demajors.demajorsapp.feature.song.DetailSongActivity
import com.demajors.demajorsapp.model.api.song.Song
import com.demajors.demajorsapp.util.GlideApp
import com.google.gson.Gson

class BestSellerAdapter(val data: MutableList<Song>) : RecyclerView.Adapter<BestSellerAdapter.BestSellerHolder>() {

    class BestSellerHolder(val binding: ViewItemBestSellerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerHolder =
        BestSellerHolder(
            ViewItemBestSellerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BestSellerHolder, position: Int) {
        with(holder) {
            GlideApp.with(itemView.context)
                .load(data[position].coverUrl)
                .centerCrop()
                .into(binding.imgThumbnail)
            binding.valueTitle.text = data[position].title
            binding.valueArtist.text = data[position].name

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailSongActivity::class.java)
                        .putExtra(DetailSongActivity.KEY_DATA, Gson().toJson(data[position]))
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
