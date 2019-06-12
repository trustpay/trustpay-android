package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.trustpay.R
import com.trustpay.adapters.OperatorAdapter
import com.trustpay.models.Model
import kotlinx.android.synthetic.main.fragment_pay.*
import kotlinx.android.synthetic.main.fragment_pay.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PayFragment : Fragment() {
    private lateinit var amount:String
    private lateinit var id:String
    private lateinit var response: Model.InitiateTransactionResponse
    private val payers = response.payers
    private val TAG = PayFragment::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        Log.i(TAG, arguments.toString())
        amount = arguments?.getString("amount").toString()
        response = arguments!!.getSerializable("response") as Model.InitiateTransactionResponse
        id = response.id
        view.tv_name.text = response.name.toUpperCase()
        view.tv_amount.text = String.format("%s XAF", amount)
        loadImage(view)
        view.image_mtn.setOnClickListener { mtnImageClick() }
        view.image_orange.setOnClickListener { orangeImageClick() }
        return view
    }

    private fun orangeImageClick() {
        activateCardPhone()
        Picasso.get().load(R.drawable.ic_mtn).resize(130,130).into(image_mtn)
        Picasso.get().load(R.drawable.ic_orange_pick).resize(130,130).into(image_orange)
    }

    private fun mtnImageClick() {
        activateCardPhone()
        Picasso.get().load(R.drawable.ic_orange).resize(130, 130).into(image_orange)
        Picasso.get().load(R.drawable.ic_mtn_pick).resize(130,130).into(image_mtn)
    }

    private fun activateCardPhone() {
        if(card_phone.visibility != VISIBLE){
            card_phone.visibility = VISIBLE
        }
    }


    private fun loadImage(view: View) {
        Picasso.get().load(R.drawable.ic_orange).resize(130, 130).into(view.image_orange)
        Picasso.get().load(R.drawable.ic_mtn).resize(130,130).into(view.image_mtn)
    }


}
