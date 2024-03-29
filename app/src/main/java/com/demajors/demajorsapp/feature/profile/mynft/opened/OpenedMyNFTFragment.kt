package com.demajors.demajorsapp.feature.profile.mynft.opened

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentOpenedMynftBinding
import com.demajors.demajorsapp.feature.profile.mynft.MyNFTViewModel

class OpenedMyNFTFragment : BaseFragment<MyNFTViewModel>() {
    override val viewModelClass: Class<MyNFTViewModel> = MyNFTViewModel::class.java
    private var _binding: FragmentOpenedMynftBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_DATA = "DATA"
        fun newInstance(data: String) = OpenedMyNFTFragment().apply {
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
        _binding = FragmentOpenedMynftBinding.inflate(inflater, container, false)
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
            ui.rv.adapter = OpenedMyNFTAdapter(viewModel.getDummyOpened())
        }
    }
}
