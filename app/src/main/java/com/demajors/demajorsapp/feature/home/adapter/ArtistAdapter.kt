package com.demajors.demajorsapp.feature.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemArtistBinding
import com.demajors.demajorsapp.feature.myartist.ArtistDetailActivity
import com.demajors.demajorsapp.model.home.ArtistItem

class ArtistAdapter(val data: MutableList<ArtistItem>) : RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    class ArtistHolder(val binding: ViewItemArtistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder =
        ArtistHolder(
            ViewItemArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        ArtistDetailActivity::class.java
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
