package com.demajors.demajorsapp.feature.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentCartBinding

class CartFragment : BaseFragment<CartViewModel>() {
    override val viewModelClass: Class<CartViewModel> = CartViewModel::class.java
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.let { ui ->
            ui.rv.layoutManager = LinearLayoutManager(activity)
            adapter = CartAdapter(viewModel.getDummyCartItems())
            ui.rv.adapter = adapter
        }
    }
}
