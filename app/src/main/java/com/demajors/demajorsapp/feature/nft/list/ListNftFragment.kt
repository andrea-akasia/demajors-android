package com.demajors.demajorsapp.feature.nft.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentNftBinding
import com.demajors.demajorsapp.feature.myartist.detail.nft.NFTAdapter

class ListNftFragment : BaseFragment<ListNFTViewModel>() {
    override val viewModelClass: Class<ListNFTViewModel> = ListNFTViewModel::class.java
    private var _binding: FragmentNftBinding? = null
    private val binding get() = _binding!!

    lateinit var trendingNFTAdapter: NFTAdapter
    lateinit var latestNFTAdapter: NFTAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.let { ui ->
            ui.rvNftTrending.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            trendingNFTAdapter = NFTAdapter(viewModel.getDummyTrendingNFTs())
            ui.rvNftTrending.adapter = trendingNFTAdapter

            ui.rvNftNew.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            latestNFTAdapter = NFTAdapter(viewModel.getDummyLatestNFTs())
            ui.rvNftNew.adapter = latestNFTAdapter
        }
    }
}