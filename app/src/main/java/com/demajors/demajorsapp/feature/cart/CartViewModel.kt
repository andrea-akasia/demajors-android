package com.demajors.demajorsapp.feature.cart

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.demajors.demajorsapp.base.BaseViewModel
import com.demajors.demajorsapp.data.DataManager
import com.demajors.demajorsapp.model.cart.CartItem
import javax.inject.Inject

@SuppressLint("CheckResult")
class CartViewModel
@Inject constructor(private val dataManager: DataManager) : BaseViewModel() {

    internal var warningMessage = MutableLiveData<String>()

    fun getDummyCartItems(): MutableList<CartItem> {
        val result = mutableListOf<CartItem>()
        result.add(CartItem())
        result.add(CartItem())
        result.add(CartItem())
        return result
    }
}
