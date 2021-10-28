package com.demajors.demajorsapp.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentProfileBinding
import com.demajors.demajorsapp.feature.login.LoginActivity

class ProfileFragment : BaseFragment<ProfileViewModel>() {
    override val viewModelClass: Class<ProfileViewModel> = ProfileViewModel::class.java
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.let { ui ->
            ui.actionLogout.setOnClickListener {
                startActivity(
                    Intent(activity, LoginActivity::class.java)
                )
            }
        }
    }
}
