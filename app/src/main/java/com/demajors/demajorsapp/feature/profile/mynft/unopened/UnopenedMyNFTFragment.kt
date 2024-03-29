package com.demajors.demajorsapp.feature.profile.mynft.unopened

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentUnopenedMynftBinding
import com.demajors.demajorsapp.feature.profile.mynft.MyNFTViewModel

class UnopenedMyNFTFragment : BaseFragment<MyNFTViewModel>() {
    override val viewModelClass: Class<MyNFTViewModel> = MyNFTViewModel::class.java
    private var _binding: FragmentUnopenedMynftBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: UnopenedMyNFTAdapter

    companion object {
        const val KEY_DATA = "DATA"
        fun newInstance(data: String) = UnopenedMyNFTFragment().apply {
            arguments = bundleOf(
                KEY_DATA to data
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUnopenedMynftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { ui ->
            ui.rv.layoutManager = GridLayoutManager(activity, 3)
            adapter = UnopenedMyNFTAdapter(viewModel.getDummyUnopened())
            ui.rv.adapter = adapter
        }
    }
}
