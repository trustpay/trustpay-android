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

import com.trustpay.R
import com.trustpay.api.Trustpay
import com.trustpay.listeners.AccountListener
import com.trustpay.listeners.CheckAccountListener
import com.trustpay.models.CheckAccount
import com.trustpay.models.Model
import kotlinx.android.synthetic.main.fragment_check_account.*
import kotlinx.android.synthetic.main.fragment_check_account.view.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CheckAccountFragment : Fragment() {

    private lateinit var trustpay: Trustpay
    private val TAG = CheckAccountFragment::class.java.simpleName
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // init var
        trustpay = arguments?.getSerializable("trustpay")!! as Trustpay
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_check_account, container, false)
        view.btn_check_account.setOnClickListener {
            btnCheckAccountClick(view)
        }
        view.text_amount.text = trustpay.getAmount().toString()
        return view
    }

    private fun btnCheckAccountClick(view: View) {
        if(edit_account.text.isNullOrBlank()){
            edit_account.error = getString(R.string.required)
        }else{
            progress_account.visibility = VISIBLE
            btn_check_account.isEnabled = false
            trustpay.phoneNumber =ccp.fullNumber+edit_account.text.toString()
            trustpay.checkAccount(object : CheckAccountListener {
                override fun onSuccess(response: CheckAccount.CheckAccountResponse) {
                    trustpay.name = response.name
                    trustpay.payers = response.payers
                    openPayFragment()
                }

                override fun onError(statusCode: Int, message: String) {
                    progress_account.visibility = GONE
                    btn_check_account.isEnabled = true
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun openPayFragment(){
        val fragment = PayFragment()
        val bundle = Bundle()
        bundle.putSerializable("trustpay", trustpay)
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.transaction_container, fragment)?.commit()
    }


}
