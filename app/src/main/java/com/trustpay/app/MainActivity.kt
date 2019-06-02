package com.trustpay.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.trustpay.activities.TransactionActivity
import com.trustpay.api.Trustpay
import com.trustpay.listeners.AccountListener
import com.trustpay.models.Model
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_pay.setOnClickListener { pay() }
    }

    private fun pay() {
        val intent = Intent(this, TransactionActivity::class.java)
        intent.putExtra("account", "237691131446")
        intent.putExtra("secret_key", "imCuBo8wb2GmGWw4")
        startActivity(intent)
    }



}
