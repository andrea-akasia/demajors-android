package com.demajors.demajorsapp.feature.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseFragment
import com.demajors.demajorsapp.databinding.FragmentProfileBinding
import com.demajors.demajorsapp.feature.login.LoginActivity
import com.demajors.demajorsapp.feature.profile.mynft.MyNftActivity
import com.demajors.demajorsapp.util.GlideApp

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

    override fun onResume() {
        super.onResume()
        viewModel.loadUserInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onUserInfoLoaded.observe(
            viewLifecycleOwner,
            {
                GlideApp.with(this)
                    .load(it.photo)
                    .placeholder(R.drawable.user_photo_placeholder)
                    .into(_binding?.imgUser!!)

                _binding?.valueUserName?.text = it.username
            }
        )

        viewModel.onLogoutSuccess.observe(
            viewLifecycleOwner,
            {
                startActivity(
                    Intent(requireContext(), LoginActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                activity?.finish()
            }
        )

        _binding?.let { ui ->
            ui.valueUserName.text = viewModel.getUsername()

            ui.actionLogout.setOnClickListener {
                openLogoutConfirmation()
            }

            ui.actionMyNft.setOnClickListener {
                startActivity(
                    Intent(requireContext(), MyNftActivity::class.java)
                )
            }
        }
    }

    private fun openLogoutConfirmation() {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setMessage("Anda Yakin Ingin Melakukan Logout?")
        dialog.setPositiveButton("YA") { _, _ ->
            viewModel.logout()
        }
        dialog.setNegativeButton("BATAL") { dialog, _ ->
            dialog.dismiss()
        }
        dialog.create().show()
    }
}
