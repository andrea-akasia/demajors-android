package com.demajors.demajorsapp.feature.profile.addresslist

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityListUserAddressBinding

class ListUserAddressActivity : BaseActivity<UserAddressViewModel>() {

    override val viewModelClass: Class<UserAddressViewModel> = UserAddressViewModel::class.java
    private lateinit var binding: ActivityListUserAddressBinding

    private lateinit var adapter: UserAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        viewModel.warningMessage.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )

        viewModel.onAuthFailed.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                this@ListUserAddressActivity.finish()
            }
        )

        viewModel.onDataLoaded.observe(
            this,
            {
                adapter = UserAddressAdapter(it.toMutableList())
                binding.rv.layoutManager = LinearLayoutManager(this)
                binding.rv.adapter = adapter
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }
}
