package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.listeners.PaymentListener
import com.trustpay.models.Model
import kotlinx.android.synthetic.main.fragment_pay.*
import kotlinx.android.synthetic.main.fragment_pay.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PayFragment : Fragment() {

    private val TAG = PayFragment::class.java.simpleName
    private lateinit var trustpay: Trustpay
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        trustpay = arguments?.getSerializable("trustpay") as Trustpay

        return view
    }









}
