package com.demajors.demajorsapp.feature.myartist.detail.merchandise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemArtistMerchandiseBinding
import com.demajors.demajorsapp.model.artist.MerchandiseItem

class MerchandiseAdapter(val data: MutableList<MerchandiseItem>) : RecyclerView.Adapter<MerchandiseAdapter.MerchandiseHolder>() {

    class MerchandiseHolder(val binding: ViewItemArtistMerchandiseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchandiseHolder =
        MerchandiseHolder(
            ViewItemArtistMerchandiseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: MerchandiseHolder, position: Int) {
        with(holder) {
        }
    }

    override fun getItemCount(): Int = data.size
}
