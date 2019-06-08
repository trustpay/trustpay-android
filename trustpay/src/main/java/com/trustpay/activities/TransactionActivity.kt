package com.trustpay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trustpay.R
import com.trustpay.fragments.CheckAccountFragment

class TransactionActivity : AppCompatActivity() {
    private val TAG= TransactionActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        val fragment = CheckAccountFragment()
        val bundle = Bundle()
        bundle.putString("secret_key", intent.extras?.getString("secret_key"))
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.transaction_container,fragment )
            .commit()


    }
}
