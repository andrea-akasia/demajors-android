package com.demajors.demajorsapp.feature.profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityUpdateProfileBinding
import com.demajors.demajorsapp.util.Const.Companion.RC_PERMISSIONS
import com.demajors.demajorsapp.util.GlideApp
import com.demajors.demajorsapp.util.createMultipartFromImageFile
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.registerImagePicker
import id.zelory.compressor.Compressor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File

@DelicateCoroutinesApi
class UpdateProfileActivity : BaseActivity<ProfileViewModel>() {

    override val viewModelClass: Class<ProfileViewModel> = ProfileViewModel::class.java
    private lateinit var binding: ActivityUpdateProfileBinding

    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private fun allPermissionsGranted(): Boolean {
        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    this, permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    private val imagePickerLauncher = registerImagePicker {
        compressImageFile(File(it[0].path))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RC_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(this, "Permission Not Granted!", Toast.LENGTH_SHORT).show()
            } else {
                imagePickerLauncher.launch(
                    config = ImagePickerConfig(
                        mode = ImagePickerMode.SINGLE
                    )
                )
            }
        }
    }

    private fun compressImageFile(imageFile: File) {
        GlobalScope.launch {
            val compressedImageFile = Compressor.compress(applicationContext, imageFile)
            Timber.i("compressed image path -> ${compressedImageFile.path}")
            viewModel.uploadImage(createMultipartFromImageFile(compressedImageFile, "client_image"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        binding.image.setOnClickListener {
            if (allPermissionsGranted()) {
                imagePickerLauncher.launch(
                    config = ImagePickerConfig(
                        mode = ImagePickerMode.SINGLE
                    )
                )
            } else {
                ActivityCompat.requestPermissions(
                    this@UpdateProfileActivity, REQUIRED_PERMISSIONS, RC_PERMISSIONS
                )
            }
        }

        binding.btnSave.setOnClickListener {
            if (binding.valueName.text.toString().isNotEmpty() &&
                binding.valueUsername.text.toString().isNotEmpty() &&
                binding.valuePhone.text.toString().isNotEmpty() &&
                viewModel.currentPhotoURL.isNotEmpty()
            ) {
                viewModel.updateProfile(
                    name = binding.valueName.text.toString(),
                    username = binding.valueUsername.text.toString(),
                    phone = binding.valuePhone.text.toString(),
                    photo = viewModel.currentPhotoURL
                )
            } else {
                Toast.makeText(this, "harap isi form dengan lengkap!", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.warningMessage.observe(
            this,
            {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        )

        viewModel.onUploadImageSuccess.observe(
            this,
            { imageURL ->
                Glide.with(this).load(imageURL).into(binding.image)
            }
        )

        viewModel.onUpdateProfileSuccess.observe(
            this,
            {
                Toast.makeText(this, "update profile berhasil", Toast.LENGTH_SHORT).show()
                this@UpdateProfileActivity.finish()
            }
        )

        viewModel.onUserInfoLoaded.observe(
            this,
            {
                GlideApp.with(this)
                    .load(it.photo)
                    .placeholder(R.drawable.user_photo_placeholder)
                    .into(binding.image)

                binding.valueName.setText(it.name)
                binding.valueUsername.setText(it.username)
                binding.valuePhone.setText(it.noTelp)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadUserInfo()
    }
}
