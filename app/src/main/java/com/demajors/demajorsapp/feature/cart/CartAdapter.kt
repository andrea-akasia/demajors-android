package com.demajors.demajorsapp.feature.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemCartMerchandiseBinding
import com.demajors.demajorsapp.model.cart.CartItem

class CartAdapter(val data: MutableList<CartItem>) : RecyclerView.Adapter<CartAdapter.CartHolder>() {

    class CartHolder(val binding: ViewItemCartMerchandiseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder =
        CartHolder(
            ViewItemCartMerchandiseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        with(holder) {
        }
    }

    override fun getItemCount(): Int = data.size
}
