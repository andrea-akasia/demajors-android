package com.demajors.demajorsapp.feature.myartist.rilisan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentArtistRilisanBinding
import com.demajors.demajorsapp.feature.myartist.ArtistViewModel

class ListRilisanFragment : BaseFragment<ArtistViewModel>() {
    override val viewModelClass: Class<ArtistViewModel> = ArtistViewModel::class.java
    private var _binding: FragmentArtistRilisanBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_DATA = "DATA"
        fun newInstance(data: String) = ListRilisanFragment().apply {
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
        _binding = FragmentArtistRilisanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { ui ->
        }
    }
}
