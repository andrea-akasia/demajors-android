package com.demajors.demajorsapp.feature.profile.mynft.unopened

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemMynftUnopenedPremiumBinding
import com.demajors.demajorsapp.databinding.ViewItemMynftUnopenedRegularBinding
import com.demajors.demajorsapp.model.artist.NFTItem

class UnopenedMyNFTAdapter(val data: MutableList<NFTItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PremiumHolder(val bindingPremium: ViewItemMynftUnopenedPremiumBinding) : RecyclerView.ViewHolder(bindingPremium.root)
    class RegularHolder(val bindingRegular: ViewItemMynftUnopenedRegularBinding) : RecyclerView.ViewHolder(bindingRegular.root)

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
                ViewItemMynftUnopenedPremiumBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else {
            RegularHolder(
                ViewItemMynftUnopenedRegularBinding.inflate(
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
                    Intent(itemView.context, UnopenedMyNFTDetailActivity::class.java)
                )
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
