package com.trustpay.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    private lateinit var amount:String
    private lateinit var id:String
    private lateinit var secretKey:String
    private  var response: Model.InitiateTransactionResponse ?=null
    private var payer:Model.Payer ?=null
    private lateinit var payers:List<Model.Payer>
    private val TAG = PayFragment::class.java.simpleName
    private val dimenImage = 130
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        Log.i(TAG, arguments.toString())
        amount = arguments?.getString("amount").toString()
        secretKey = arguments?.getString("secretkey").toString()
        response = arguments!!.getSerializable("response") as Model.InitiateTransactionResponse
        id = response?.id!!
        payers = response!!.payers
        view.tv_name.text = response?.name?.toUpperCase()
        view.tv_amount.text = String.format("%s XAF", amount)
        loadImage(view)
        view.image_mtn.setOnClickListener { mtnImageClick() }
        view.image_orange.setOnClickListener { orangeImageClick() }
        view.btn_valid.setOnClickListener { btnValidClick(it) }
        return view
    }

    private fun btnValidClick(view: View?) {
        if(edit_phone.text.isNullOrBlank()){
            edit_phone.error = getString(R.string.required)
        }else if(edit_phone.text.toString().length != 9){
            edit_phone.error = getString(R.string.invalid_phone)
        }
        else{
            val trustpay = Trustpay(secretKey =secretKey )
            trustpay.payAsync(Model.PaymentRequest(secretKey,id,payWith = payer!!.id, accountPay = getPhoneNumber(edit_phone.text.toString()) ),
                object : PaymentListener {
                    override fun onSuccess() {
                        Toast.makeText(context!!, "ok", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(status: Int, message: String) {
                        Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    private fun getPhoneNumber(phone: String): String {
        return String.format("237%s", phone)
    }

    private fun orangeImageClick() {
        payer = payers[0]
        activateCardPhone()
        Picasso.get().load(R.drawable.ic_mtn).resize(dimenImage,dimenImage).into(image_mtn)
        Picasso.get().load(R.drawable.ic_orange_pick).resize(dimenImage,dimenImage).into(image_orange)
    }

    private fun mtnImageClick() {
        payer = payers[1]
        activateCardPhone()
        Picasso.get().load(R.drawable.ic_orange).resize(dimenImage, dimenImage).into(image_orange)
        Picasso.get().load(R.drawable.ic_mtn_pick).resize(dimenImage,dimenImage).into(image_mtn)
    }

    private fun activateCardPhone() {
        if(card_phone.visibility != VISIBLE){
            card_phone.visibility = VISIBLE
        }
    }


    private fun loadImage(view: View) {
        Picasso.get().load(R.drawable.ic_orange).resize(dimenImage, dimenImage).into(view.image_orange)
        Picasso.get().load(R.drawable.ic_mtn).resize(dimenImage,dimenImage).into(view.image_mtn)
    }


}
