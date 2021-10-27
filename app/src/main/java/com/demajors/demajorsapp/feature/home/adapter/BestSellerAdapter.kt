package com.demajors.demajorsapp.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemBestSellerBinding
import com.demajors.demajorsapp.model.home.HomeItem

class BestSellerAdapter(val data: MutableList<HomeItem>) : RecyclerView.Adapter<BestSellerAdapter.BestSellerHolder>() {

    class BestSellerHolder(val binding: ViewItemBestSellerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerHolder =
        BestSellerHolder(
            ViewItemBestSellerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: BestSellerHolder, position: Int) {
        with(holder) {
        }
    }

    override fun getItemCount(): Int = data.size
}