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
import com.trustpay.listeners.InitiateListener
import com.trustpay.listeners.InitiateTransactionListerner
import com.trustpay.models.Initiate
import com.trustpay.models.Model
import javax.crypto.SecretKey


/**
 * A simple [Fragment] subclass.
 *
 */
class InitiateTransaction : Fragment() {

    private lateinit var trustpay: Trustpay
    private val TAG = InitiateTransaction::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_initiate_transaction, container, false)
        trustpay = arguments?.getSerializable("trustpay")!! as Trustpay
        Log.i(TAG, "Hi")
        trustpay.initiate(object :  InitiateListener{

            override fun onSuccess(response: Initiate.InitiateResponse) {
                trustpay.key = response.key
                openCheckAccount()
            }

            override fun onError(statusCode: Int, message: String) {
                Log.i(TAG, message)
               showError(message, view)
            }

        })
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    private fun openCheckAccount(){
        val fragment = CheckAccountFragment()
        val bundle = Bundle()
        bundle.putSerializable("trustpay", trustpay)
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.transaction_container, fragment)?.commit()
    }

    private fun showError(message:String, view: View){
        Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
        activity?.finish()
    }




}
