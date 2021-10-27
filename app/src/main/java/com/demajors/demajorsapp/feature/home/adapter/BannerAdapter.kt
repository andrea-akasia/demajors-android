package com.demajors.demajorsapp.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.databinding.ViewItemBannerBinding
import com.demajors.demajorsapp.model.banner.Banner

class BannerAdapter(val data: MutableList<Banner>) : RecyclerView.Adapter<BannerAdapter.BannerHolder>() {

    class BannerHolder(val binding: ViewItemBannerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerHolder =
        BannerHolder(
            ViewItemBannerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        with(holder) {
            binding.img.setImageResource(R.drawable.sample_banner)
        }
    }

    override fun getItemCount(): Int = data.size
}
