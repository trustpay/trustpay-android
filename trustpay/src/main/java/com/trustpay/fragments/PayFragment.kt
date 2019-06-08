package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trustpay.R
import kotlinx.android.synthetic.main.fragment_pay.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PayFragment : Fragment() {
    private lateinit var amount:String
    private lateinit var id:String
    private lateinit var name:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        name = arguments?.getString("name").toString()
        amount = arguments?.getString("amount").toString()
        id = arguments?.getString("id").toString()
        view.tv_name.text = name
        view.tv_amount.text = amount
        return view
    }


}
