package com.demajors.demajorsapp.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentHomeBinding
import com.demajors.demajorsapp.feature.home.adapter.BannerAdapter
import com.demajors.demajorsapp.feature.home.adapter.BestSellerAdapter
import com.demajors.demajorsapp.feature.home.adapter.LatestReleaseAdapter
import com.demajors.demajorsapp.feature.home.adapter.RecommendationAdapter
import com.demajors.demajorsapp.feature.home.adapter.ArtistAdapter
import com.demajors.demajorsapp.feature.myartist.detail.nft.NFTAdapter

class HomeFragment : BaseFragment<HomeViewModel>() {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var bannerAdapter: BannerAdapter? = null
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var rilisanAdapter: LatestReleaseAdapter
    private lateinit var songAdapter: BestSellerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.let { ui ->

            /*--------------------------- LIST ARTISTS --------------------------------------  */
            viewModel.onArtistLoaded.observe(
                viewLifecycleOwner,
                {
                    artistAdapter = ArtistAdapter(it.toMutableList())
                    ui.rvArtist.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = artistAdapter
                    }
                }
            )

            /*--------------------------- LIST RILISAN --------------------------------------  */
            viewModel.onRilisanLoaded.observe(
                viewLifecycleOwner,
                {
                    rilisanAdapter = LatestReleaseAdapter(it.toMutableList())
                    ui.rvLatest.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = rilisanAdapter
                    }
                }
            )

            /*--------------------------- LIST SONG BEST SELLER --------------------------------------  */
            viewModel.onSongLoaded.observe(
                viewLifecycleOwner,
                {
                    songAdapter = BestSellerAdapter(it.toMutableList())
                    ui.rvBestSeller.apply {
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                        adapter = songAdapter
                    }
                }
            )

            bannerAdapter = BannerAdapter(viewModel.getDummyBanner())
            ui.viewBanner.adapter = bannerAdapter
            ui.bannerIndicator.setViewPager2(ui.viewBanner)

            ui.rvRecommendations.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ui.rvRecommendations.adapter = RecommendationAdapter(viewModel.getDummyHomeItems())

            ui.rvNft.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ui.rvNft.adapter = NFTAdapter(viewModel.getDummyPremiumNFTs())
        }

        viewModel.loadListArtist()
        viewModel.loadListRilisan()
        viewModel.loadListSong()
    }
}
