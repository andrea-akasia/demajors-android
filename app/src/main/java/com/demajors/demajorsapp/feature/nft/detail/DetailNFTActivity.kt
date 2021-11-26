package com.demajors.demajorsapp.feature.nft.detail

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.demajors.demajorsapp.BuildConfig
import com.demajors.demajorsapp.R
import com.demajors.demajorsapp.base.BaseActivity
import com.demajors.demajorsapp.databinding.ActivityDetailNftBinding
import com.demajors.demajorsapp.util.getCurrentTimeISO
import com.google.gson.Gson
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.UIKitCustomSetting
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ExpiryModel
import com.midtrans.sdk.corekit.models.ItemDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.corekit.models.snap.Authentication
import com.midtrans.sdk.corekit.models.snap.CreditCard
import com.midtrans.sdk.corekit.models.snap.TransactionResult
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import timber.log.Timber

class DetailNFTActivity : BaseActivity<DetailNFTViewModel>(), TransactionFinishedCallback {

    override val viewModelClass: Class<DetailNFTViewModel> = DetailNFTViewModel::class.java
    private lateinit var binding: ActivityDetailNftBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNftBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)

        binding.actionBack.setOnClickListener { onBackPressed() }

        binding.btnBuy.setOnClickListener {
            pay()
        }

        initMidtrans()
    }

    private fun pay() {
        val dummyTrxId = System.currentTimeMillis().toString()
        val dummyAmount = 1000000.0
        val items = ArrayList<ItemDetails>()
        items.add(ItemDetails("itemiddummy101", dummyAmount, 1, "itemdummyname101"))
        val dummyCustomer = CustomerDetails().apply {
            customerIdentifier = "test-customer101"
            firstName = "test"
            shippingAddress = ShippingAddress().apply {
                address = "Dummy Address"
            }
        }
        val transactionRequest = TransactionRequest(dummyTrxId, dummyAmount).apply {
            customerDetails = dummyCustomer
            itemDetails = items
            creditCard = CreditCard().apply {
                isSaveCard = false
                authentication = Authentication.AUTH_3DS
            }
            expiry = ExpiryModel().apply {
                startTime = getCurrentTimeISO(System.currentTimeMillis())
                duration = 1
                unit = ExpiryModel.UNIT_HOUR
            }
        }

        MidtransSDK.getInstance().transactionRequest = transactionRequest
        MidtransSDK.getInstance().startPaymentUiFlow(this)
    }

    private fun initMidtrans() {
        SdkUIFlowBuilder.init()
            .setClientKey(BuildConfig.CLIENT_KEY)
            .setContext(this)
            .setTransactionFinishedCallback(this)
            .setMerchantBaseUrl(BuildConfig.BASE_URL)
            .enableLog(true)
            .setUIkitCustomSetting(
                UIKitCustomSetting().apply {
                    isSkipCustomerDetailsPages = true
                }
            )
            .buildSDK()
    }

    override fun onTransactionFinished(result: TransactionResult) {
        if (result.isTransactionCanceled) {
            Timber.w(Throwable("transaction canceled"))
            Toast.makeText(this, "Transaction Canceled", Toast.LENGTH_LONG).show()
        } else {
            when (result.status) {
                TransactionResult.STATUS_SUCCESS -> {
                    Timber.i("PAYMENT SUCCESS")
                    Timber.i("PAYMENT RESULT: ${Gson().toJson(result)}")
                    Toast.makeText(this, "Transaction Success", Toast.LENGTH_LONG).show()
                }
                TransactionResult.STATUS_PENDING -> {
                    Timber.i("PAYMENT PENDING")
                    Timber.i("PAYMENT RESULT: ${Gson().toJson(result)}")
                    Toast.makeText(this, "Transaction Pending", Toast.LENGTH_LONG).show()
                }
                TransactionResult.STATUS_FAILED -> {
                    Timber.i("PAYMENT FAILED")
                    Timber.i(Throwable("PAYMENT RESULT: ${Gson().toJson(result)}"))
                    Toast.makeText(this, "Transaction Failed", Toast.LENGTH_LONG).show()
                }
                TransactionResult.STATUS_INVALID -> {
                    Timber.w("PAYMENT INVALID")
                    Timber.w(Throwable("PAYMENT RESULT: ${Gson().toJson(result)}"))
                    Toast.makeText(this, "Transaction Invalid", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
