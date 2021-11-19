package com.demajors.demajorsapp.feature.myartist.detail.rilisan

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemArtistReleaseBinding
import com.demajors.demajorsapp.feature.rilisan.DetailRilisanActivity
import com.demajors.demajorsapp.model.artist.RilisanItem

class ArtistReleaseAdapter(val data: MutableList<RilisanItem>) : RecyclerView.Adapter<ArtistReleaseAdapter.ArtistReleaseHolder>() {

    class ArtistReleaseHolder(val binding: ViewItemArtistReleaseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistReleaseHolder =
        ArtistReleaseHolder(
            ViewItemArtistReleaseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistReleaseHolder, position: Int) {
        with(holder) {
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailRilisanActivity::class.java)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
