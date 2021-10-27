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
import com.demajors.demajorsapp.feature.home.adapter.NowPLayingAdapter

class HomeFragment : BaseFragment<HomeViewModel>() {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var bannerAdapter: BannerAdapter? = null

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
            bannerAdapter = BannerAdapter(viewModel.getDummyBanner())
            ui.viewBanner.adapter = bannerAdapter
            ui.bannerIndicator.setViewPager2(ui.viewBanner)

            ui.rvBestSeller.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ui.rvBestSeller.adapter = BestSellerAdapter(viewModel.getDummyHomeItems())

            ui.rvNowPlaying.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            ui.rvNowPlaying.adapter = NowPLayingAdapter(viewModel.getDummyHomeItems())
        }
    }
}
