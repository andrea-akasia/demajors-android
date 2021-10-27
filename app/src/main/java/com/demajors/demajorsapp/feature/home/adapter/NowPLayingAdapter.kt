package com.demajors.demajorsapp.feature.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemNowPlayingBinding
import com.demajors.demajorsapp.model.home.HomeItem

class NowPLayingAdapter(val data: MutableList<HomeItem>) : RecyclerView.Adapter<NowPLayingAdapter.NowPLayingHolder>() {

    class NowPLayingHolder(val binding: ViewItemNowPlayingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPLayingHolder =
        NowPLayingHolder(
            ViewItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: NowPLayingHolder, position: Int) {
        with(holder) {
        }
    }

    override fun getItemCount(): Int = data.size
}
