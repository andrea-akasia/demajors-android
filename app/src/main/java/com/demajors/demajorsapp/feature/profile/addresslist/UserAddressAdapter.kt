package com.demajors.demajorsapp.feature.profile.addresslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demajors.demajorsapp.databinding.ViewItemUserAddressBinding
import com.demajors.demajorsapp.model.api.profile.address.UserAddress

class UserAddressAdapter(val data: MutableList<UserAddress>) : RecyclerView.Adapter<UserAddressAdapter.UserAddressHolder>() {

    interface UserAddressListener {
        fun onDelete(data: UserAddress)
    }

    var listener: UserAddressListener? = null

    class UserAddressHolder(val binding: ViewItemUserAddressBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAddressHolder =
        UserAddressHolder(
            ViewItemUserAddressBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserAddressHolder, position: Int) {
        with(holder) {
            if (data[bindingAdapterPosition].isDefault == 1) {
                binding.imgIsDefault.visibility = View.VISIBLE
            } else {
                binding.imgIsDefault.visibility = View.GONE
            }

            binding.valueAddress.text = data[bindingAdapterPosition].address
            binding.valueCoordinate.text = "${data[bindingAdapterPosition].latitude}, ${data[bindingAdapterPosition].longitude}"
            binding.actionDelete.setOnClickListener {
                listener?.onDelete(data[bindingAdapterPosition])
            }
        }
    }

    override fun getItemCount(): Int = data.size
}
