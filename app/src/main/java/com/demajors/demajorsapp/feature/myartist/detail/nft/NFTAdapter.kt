package com.demajors.demajorsapp.feature.myartist.detail.nft

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemNftPremiumBinding
import com.demajors.demajorsapp.databinding.ViewItemNftRegulerBinding
import com.demajors.demajorsapp.feature.nft.detail.DetailNFTActivity
import com.demajors.demajorsapp.model.artist.NFTItem

class NFTAdapter(val data: MutableList<NFTItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PremiumHolder(val bindingPremium: ViewItemNftPremiumBinding) : RecyclerView.ViewHolder(bindingPremium.root)
    class RegularHolder(val bindingRegular: ViewItemNftRegulerBinding) : RecyclerView.ViewHolder(bindingRegular.root)

    override fun getItemViewType(position: Int): Int {
        return if (data[position].type == "PREMIUM") {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            PremiumHolder(
                ViewItemNftPremiumBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            RegularHolder(
                ViewItemNftRegulerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder) {
            if (itemViewType == 0) {
                val vh = holder as PremiumHolder
                val binding = vh.bindingPremium
            } else {
                val vh = holder as RegularHolder
                val binding = vh.bindingRegular
            }
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailNFTActivity::class.java)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
