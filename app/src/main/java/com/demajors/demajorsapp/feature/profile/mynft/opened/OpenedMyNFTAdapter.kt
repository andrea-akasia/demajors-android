package com.demajors.demajorsapp.feature.profile.mynft.opened

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemMynftOpenedBinding
import com.demajors.demajorsapp.model.artist.NFTItem

class OpenedMyNFTAdapter(val data: MutableList<NFTItem>) : RecyclerView.Adapter<OpenedMyNFTAdapter.OpenedNFTHolder>() {

    class OpenedNFTHolder(val bindingPremium: ViewItemMynftOpenedBinding) : RecyclerView.ViewHolder(bindingPremium.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenedNFTHolder {
        return OpenedNFTHolder(
            ViewItemMynftOpenedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OpenedNFTHolder, position: Int) {
        with(holder) {
        }
    }

    override fun getItemCount(): Int = data.size
}
