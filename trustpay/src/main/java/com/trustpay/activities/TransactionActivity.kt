package com.trustpay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.fragments.CheckAccountFragment
import com.trustpay.fragments.InitiateTransaction

class TransactionActivity : AppCompatActivity() {
    private val TAG= TransactionActivity::class.java.simpleName
    private  lateinit var trustpay: Trustpay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val fragment = InitiateTransaction()
        val bundle = Bundle()
        trustpay = Trustpay(intent.extras?.getString("secret_key")!!,intent.extras?.getInt("amount")!!.toInt(),
                    intent.extras?.getString("ref").toString() )
        bundle.putSerializable("trustpay", trustpay)

        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.transaction_container,fragment )
            .commit()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
