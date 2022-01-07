package com.demajors.demajorsapp.feature.profile.addresslist

import android.Manifest
import android.annotation.SuppressLint
import android.content.IntentSender
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityListUserAddressBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

class ListUserAddressActivity :
    BaseActivity<UserAddressViewModel>(),
    CreateAddressDialog.SaveListener {

    override val viewModelClass: Class<UserAddressViewModel> = UserAddressViewModel::class.java
    private lateinit var binding: ActivityListUserAddressBinding

    private lateinit var adapter: UserAddressAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var cancellationSource: CancellationTokenSource? = null

    val locationSetting = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        Timber.d("StartIntentSenderForResult -> ${result.resultCode}")
        if (result.resultCode == RESULT_OK) {
            Timber.i("gps turned on")
            getCurrentLocation()
        } else {
            Timber.w("gps need to be turned on")
        }
    }

    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        var isAllGranted = true
        permissions.entries.forEach {
            Timber.d("${it.key} -> ${it.value}")
            if (!it.value) { isAllGranted = false }
        }

        if (isAllGranted) {
            Timber.i("Permission Granted")
            showEnableLocationSetting()
        } else {
            Timber.i("Permission not granted")
            Toast.makeText(this, "tidak dapat menambah alamat. mohon berikan izin akses lokasi terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }
        binding.actionAdd.setOnClickListener {
            if (isPermissionsGranted()) {
                Timber.i("Permission already Granted")
                showEnableLocationSetting()
            } else {
                requestPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }
        }

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
                if (it.isNotEmpty()) {
                    adapter = UserAddressAdapter(it.toMutableList())
                    binding.rv.layoutManager = LinearLayoutManager(this)
                    binding.rv.adapter = adapter

                    binding.rv.visibility = View.VISIBLE
                    binding.viewEmpty.visibility = View.GONE
                } else {
                    binding.rv.visibility = View.GONE
                    binding.viewEmpty.visibility = View.VISIBLE
                }
            }
        )

        viewModel.onDataCreated.observe(
            this,
            {
                onResume()
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun isPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        Snackbar.make(binding.parent, "Mencari lokasi anda...", Snackbar.LENGTH_LONG).show()

        cancellationSource = CancellationTokenSource()
        val currentLocationTask = fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, cancellationSource!!.token)
        currentLocationTask.addOnSuccessListener { location ->
            Timber.d("currentLocationTask Location -> ${location.latitude}, ${location.longitude}")
            val createDialog = CreateAddressDialog().newInstance(location.latitude, location.longitude)
            createDialog.listener = this@ListUserAddressActivity
            createDialog.show(supportFragmentManager, null)
        }
        currentLocationTask.addOnFailureListener {
            Timber.e(Throwable(it))
            Snackbar.make(binding.parent, "Gagal mendapatkan lokasi! ${it.localizedMessage}", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showEnableLocationSetting() {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)

        val task = LocationServices.getSettingsClient(this)
            .checkLocationSettings(builder.build())

        task.addOnSuccessListener { response ->
            val states = response.locationSettingsStates
            getCurrentLocation()
        }
        task.addOnFailureListener { err ->
            Timber.e(err, "settingsResponseTask failure")
            when ((err as? ApiException)?.statusCode) {
                LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                    try {
                        val resolvable = err as ResolvableApiException
                        val intentSenderRequest =
                            IntentSenderRequest.Builder(resolvable.resolution).build()
                        locationSetting.launch(intentSenderRequest)
                    } catch (sie: IntentSender.SendIntentException) {
                        Timber.e("GPS: Unable to execute request.")
                    } catch (cce: java.lang.ClassCastException) {
                        // Ignore, should be an impossible error.
                        Timber.e("GPS: Unable to execute request, ClassCastException.")
                    }
            }
        }
    }

    override fun onSave(latitude: Double, longitude: Double, address: String) {
        Timber.d("onSave $address: $latitude,$longitude")
        viewModel.create(latitude, longitude, address)
    }
}
