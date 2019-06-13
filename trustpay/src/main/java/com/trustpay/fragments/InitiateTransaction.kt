package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.listeners.InitiateTransactionListerner
import com.trustpay.models.Model
import javax.crypto.SecretKey


/**
 * A simple [Fragment] subclass.
 *
 */
class InitiateTransaction : Fragment() {

    private  var secretKey: String? = null
    private  var amount:Int = 0
    private  var ref:String? = null
    private  var account:String? = null
    private lateinit var trustpay: Trustpay
    private val TAG = InitiateTransaction::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_initiate_transaction, container, false)
        secretKey = arguments?.getString("secret_key")
        amount = arguments!!.getInt("amount", 0)
        ref = arguments?.getString("ref")
        account = arguments?.getString("account_pay")
        trustpay = Trustpay(secretKey!!)
        return view
    }

    override fun onStart() {
        super.onStart()
        trustpay.initiateTransactionAsync(Model.InitiateTransactionRequest(secretKey!!, phoneNumber = account!!, ref = ref!!, amount = amount),
            object : InitiateTransactionListerner {
                override fun onError(status: Int, message: String) {
                    Log.e(TAG, message)
                }

                override fun onSucces(response: Model.InitiateTransactionResponse) {
                   val fragment = PayFragment()
                    val bundle = Bundle()
                    bundle.putString("amount", amount.toString())
                    bundle.putString("secretkey",secretKey)
                    bundle.putSerializable("response", response)
                    fragment.arguments = bundle
                    activity!!.supportFragmentManager.beginTransaction()
                                .replace(R.id.transaction_container, fragment)
                                .commit()
                }

            })
    }




}
