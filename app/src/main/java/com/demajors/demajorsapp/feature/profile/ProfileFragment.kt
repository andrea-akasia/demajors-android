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
import com.demajors.demajorsapp.feature.main.MainActivity
import com.demajors.demajorsapp.feature.profile.addresslist.ListUserAddressActivity
import com.demajors.demajorsapp.feature.profile.mynft.MyNftActivity
import com.demajors.demajorsapp.util.GlideApp
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
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

        viewModel.onAuthFailed.observe(
            viewLifecycleOwner,
            {
                _binding?.let { ui ->
                    ui.viewUnauthenticated.visibility = View.VISIBLE
                    ui.viewLogged.visibility = View.GONE
                }
            }
        )

        viewModel.onUserInfoLoaded.observe(
            viewLifecycleOwner,
            {
                GlideApp.with(this)
                    .load(it.photo)
                    .placeholder(R.drawable.user_photo_placeholder)
                    .into(_binding?.imgUser!!)

                _binding?.valueUserName?.text = it.email
            }
        )

        viewModel.onLogoutSuccess.observe(
            viewLifecycleOwner,
            {
                startActivity(
                    Intent(requireContext(), MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                )
                activity?.finish()
            }
        )

        _binding?.let { ui ->
            if (viewModel.isLoggedIn()) {
                ui.viewUnauthenticated.visibility = View.GONE
                ui.viewLogged.visibility = View.VISIBLE
                viewModel.loadUserInfo()
            } else {
                ui.viewUnauthenticated.visibility = View.VISIBLE
                ui.viewLogged.visibility = View.GONE
            }

            ui.btnGotoLogin.setOnClickListener {
                startActivity(
                    Intent(requireContext(), LoginActivity::class.java)
                )
            }

            ui.actionLogout.setOnClickListener {
                openLogoutConfirmation()
            }

            ui.actionMyNft.setOnClickListener {
                startActivity(
                    Intent(requireContext(), MyNftActivity::class.java)
                )
            }

            ui.actionProfile.setOnClickListener {
                startActivity(
                    Intent(requireContext(), UpdateProfileActivity::class.java)
                )
            }

            ui.actionAddress.setOnClickListener {
                startActivity(
                    Intent(requireContext(), ListUserAddressActivity::class.java)
                )
            }
        }
    }

    private fun openLogoutConfirmation() {
        val logoutDialog = AlertDialog.Builder(requireContext())
        logoutDialog.setMessage("Anda Yakin Ingin Melakukan Logout?")
        logoutDialog.setPositiveButton("YA") { _, _ ->
            viewModel.logout()
        }
        logoutDialog.setNegativeButton("BATAL") { dialog, _ ->
            dialog.dismiss()
        }
        logoutDialog.create().show()
    }
}
