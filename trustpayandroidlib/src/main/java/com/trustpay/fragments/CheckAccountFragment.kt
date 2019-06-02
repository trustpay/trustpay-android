package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.listeners.AccountListener
import com.trustpay.models.Model
import kotlinx.android.synthetic.main.fragment_check_account.*
import kotlinx.android.synthetic.main.fragment_check_account.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CheckAccountFragment : Fragment() {

    private lateinit var secretKey:String
    private lateinit var trustpay: Trustpay
    private val TAG = CheckAccountFragment::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // init var
        secretKey = arguments?.getString("secret_key")!!
        trustpay = Trustpay(secretKey)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_check_account, container, false)
        view.btn_check_account.setOnClickListener {
            btnCheckAccountClick()
        }
        return view
    }

    private fun btnCheckAccountClick() {
        if(edit_account.text.isNullOrBlank()){
            edit_account.error = getString(R.string.required)
        }else{
            trustpay.getAccountDetailsAsync(edit_account.text.toString(), object :AccountListener{
                override fun onSuccess(account: Model.Account) {
                    Log.i(TAG, account.toString())
                }

                override fun onError(error: String) {
                    Log.e(TAG, error)
                }

            })
        }
    }


}
