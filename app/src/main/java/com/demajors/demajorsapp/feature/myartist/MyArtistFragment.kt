package com.demajors.demajorsapp.feature.myartist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentMyArtistBinding
import kotlinx.coroutines.flow.collectLatest
import timber.log.Timber

class MyArtistFragment : BaseFragment<MyArtistViewModel>() {
    override val viewModelClass: Class<MyArtistViewModel> = MyArtistViewModel::class.java
    private var _binding: FragmentMyArtistBinding? = null
    private val binding get() = _binding!!

    private lateinit var artistAdapter: MyArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { ui ->
            artistAdapter = MyArtistAdapter()
            ui.rv.apply {
                layoutManager = GridLayoutManager(activity, 3)
                adapter = artistAdapter
            }

            artistAdapter.addLoadStateListener { loadStates ->
                when (loadStates.refresh) {
                    is LoadState.Loading -> {
                        // show progressbar
                        ui.viewLoading.visibility = View.VISIBLE
                        ui.rv.visibility = View.GONE
                    }
                    !is LoadState.Loading -> {
                        // show data and can retry
                        ui.viewLoading.visibility = View.GONE
                        ui.rv.visibility = View.VISIBLE
                    }
                    is LoadState.Error -> {
                        val errorState = loadStates.refresh as LoadState.Error
                        Timber.e(Throwable(errorState.error.cause))
                        Toast.makeText(requireContext(), errorState.error.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            viewLifecycleOwner.lifecycleScope.launchWhenCreated {
                viewModel.artistFlow.collectLatest { pagingData ->
                    artistAdapter.submitData(pagingData)
                }
            }
        }

        viewModel.loadListData()
    }
}
