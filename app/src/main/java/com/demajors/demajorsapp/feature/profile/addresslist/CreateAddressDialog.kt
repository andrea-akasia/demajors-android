package com.demajors.demajorsapp.feature.profile.addresslist

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.demajors.demajorsapp.R

class CreateAddressDialog : DialogFragment() {

    fun newInstance(latitude: Double, longitude: Double): CreateAddressDialog {
        val df = CreateAddressDialog()
        val args = Bundle()
        args.putDouble("lat", latitude)
        args.putDouble("lng", longitude)
        df.arguments = args
        return df
    }

    interface SaveListener {
        fun onSave(latitude: Double, longitude: Double, address: String)
    }
    lateinit var listener: SaveListener
    var lat: Double = 0.0
    var lng: Double = 0.0
    lateinit var valueAddress: EditText

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window ?: return
        val params = window.attributes
        params.width = 650
        window.attributes = params
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.dialog_create_user_address, container)
        dialog?.let { dialog ->
            dialog.window?.let { window ->
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window.requestFeature(Window.FEATURE_NO_TITLE)
            }

            valueAddress = view.findViewById<EditText>(R.id.value_address)

            view.findViewById<Button>(R.id.btn_save).setOnClickListener {
                if (valueAddress.text.toString().isNotEmpty()) {
                    listener.onSave(lat, lng, valueAddress.text.toString())
                    dismiss()
                } else {
                    Toast.makeText(context, "alamt tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }

            arguments?.let { args ->
                lat = args.getDouble("lat")
                lng = args.getDouble("lng")
            }
        }

        return view
    }
}
