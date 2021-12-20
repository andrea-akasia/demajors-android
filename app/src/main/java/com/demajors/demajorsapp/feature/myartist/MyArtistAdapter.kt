package com.demajors.demajorsapp.feature.myartist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemArtistBinding
import com.demajors.demajorsapp.feature.myartist.detail.ArtistDetailActivity
import com.demajors.demajorsapp.model.api.artist.Artist
import com.demajors.demajorsapp.util.GlideApp

class MyArtistAdapter : PagingDataAdapter<Artist, MyArtistAdapter.ArtistHolder>(DataComparator) {

    object DataComparator : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem == newItem
    }

    class ArtistHolder(val binding: ViewItemArtistBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder =
        ArtistHolder(
            ViewItemArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        with(holder) {
            GlideApp.with(itemView.context)
                .load(getItem(position)?.avatarUrl)
                .centerCrop()
                .into(binding.img)
            binding.valueName.text = getItem(position)?.name

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
}
