package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trustpay.R
import com.trustpay.models.Model


/**
 * A simple [Fragment] subclass.
 *
 */
class PayFragment : Fragment() {
    private lateinit var response: Model.InitiateTransactionResponse
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
       // response = arguments!!.getSerializable("response") as Model.InitiateTransactionResponse
        return view
    }


}
