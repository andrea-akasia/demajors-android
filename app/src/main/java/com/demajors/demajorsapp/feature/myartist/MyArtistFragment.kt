package com.demajors.demajorsapp.feature.myartist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentMyArtistBinding

class MyArtistFragment : BaseFragment<MyArtistViewModel>() {
    override val viewModelClass: Class<MyArtistViewModel> = MyArtistViewModel::class.java
    private var _binding: FragmentMyArtistBinding? = null
    private val binding get() = _binding!!

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
            ui.rv.layoutManager = GridLayoutManager(activity, 3)
            ui.rv.adapter = MyArtistAdapter(viewModel.getDummyArtistItems())
        }
    }
}
