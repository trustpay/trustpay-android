package com.trustpay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trustpay.R
import com.trustpay.fragments.CheckAccountFragment
import com.trustpay.fragments.InitiateTransaction

class TransactionActivity : AppCompatActivity() {
    private val TAG= TransactionActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val fragment = InitiateTransaction()
        val bundle = Bundle()
        bundle.putString("secret_key", intent.extras?.getString("secret_key"))
        bundle.putString("account_pay", intent.extras?.getString("account"))
        bundle.putInt("amount", intent.extras?.getInt("amount")!!)
        bundle.putString("ref", intent.extras?.getString("ref"))
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.transaction_container,fragment )
            .commit()


    }
}
