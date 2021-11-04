package com.demajors.demajorsapp.feature.myartist.detail.nft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentArtistNftListBinding
import com.demajors.demajorsapp.feature.myartist.detail.ArtistViewModel

class ListArtistNFTFragment : BaseFragment<ArtistViewModel>() {
    override val viewModelClass: Class<ArtistViewModel> = ArtistViewModel::class.java
    private var _binding: FragmentArtistNftListBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_DATA = "DATA"
        fun newInstance(data: String) = ListArtistNFTFragment().apply {
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
        _binding = FragmentArtistNftListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { ui ->
            ui.rvPremium.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            val premiumAdapter = NFTAdapter(viewModel.getDummyPremiumNFTs())
            ui.rvPremium.adapter = premiumAdapter

            ui.rvReguler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ui.rvReguler.adapter = NFTAdapter(viewModel.getDummyRegularNFTs())
        }
    }
}
