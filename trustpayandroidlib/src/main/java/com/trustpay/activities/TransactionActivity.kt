package com.trustpay.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trustpay.R
import com.trustpay.fragments.InitiateTransactionFragment

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        supportFragmentManager.beginTransaction()
            .add(R.id.transaction_container, InitiateTransactionFragment())
            .commit()
    }
}
