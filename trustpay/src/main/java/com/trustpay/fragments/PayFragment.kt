package com.trustpay.fragments


import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.listeners.PaymentListener
import kotlinx.android.synthetic.main.fragment_pay.*
import kotlinx.android.synthetic.main.fragment_pay.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class PayFragment : Fragment() {

    private val TAG = PayFragment::class.java.simpleName
    private lateinit var trustpay: Trustpay
    private  var payerId:String?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pay, container, false)
        trustpay = arguments?.getSerializable("trustpay") as Trustpay
        view.tv_name.text = trustpay.name
        view.tv_amount.text = String.format("%s XAF",trustpay.getAmount().toString() )
        view.ic_orange.setOnClickListener { pickOrnage() }
        view.ic_mtn.setOnClickListener { pickMtn() }
        view.btn_pay.setOnClickListener { btnPayClick(it) }
        return view
    }

    private fun btnPayClick(view: View?) {

        if(payerId == null){
            Toast.makeText(context!!, "Veuillez selectionnez un mode de paiement", Toast.LENGTH_SHORT).show()
        }else{
            btn_pay.isEnabled = false
            pay_loader.visibility = VISIBLE
            trustpay.pay(payerId!!, object : PaymentListener {
                override fun onSuccess() {
                    pay_loader.visibility = GONE
                    Toast.makeText(context!!, "OK", Toast.LENGTH_SHORT).show()
                    activity?.finish()
                }

                override fun onError(status: Int, message: String) {
                    pay_loader.visibility = GONE
                    Toast.makeText(context!!, message, Toast.LENGTH_SHORT).show()
                    activity?.finish()
                }
            })
        }

    }

    /**
     * Click Handler for MTN
     */
    private fun pickMtn() {
        removeBackground(ic_orange)
        addBackground(ic_mtn)
        // select a payer
        setPayerId(trustpay.payers[1].payerId)

    }

    /**
     * Click Handler for Orange Money
     */
    private fun pickOrnage() {
        removeBackground(ic_mtn)
        addBackground(ic_orange)
        // select a payer
        setPayerId(trustpay.payers[0].payerId)
    }

    /**
     * Remove background
     * @param imageView
     */
    private fun removeBackground(imageView: ImageView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.background = activity?.getDrawable(R.color.white)
        }else{
            Picasso.get().load(R.color.white).into(imageView)
        }
    }

    private fun setPayerId(payerId:String){
        this.payerId = payerId
    }
    private fun addBackground(imageView: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.background = activity?.getDrawable(R.drawable.back_red)
        } else {
            Picasso.get().load(R.drawable.back_red).into(imageView);
        }
    }


}
