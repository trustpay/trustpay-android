package com.trustpay.api

import com.trustpay.ApiClient
import com.trustpay.TransactionApi
import com.trustpay.listeners.AccountListener
import com.trustpay.listeners.PaymentListener
import com.trustpay.models.Model
import com.trustpay.models.Model.PaymentRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author Gildas Tema <gildastema3@gmail.com>
 */
class Trustpay(private val secretKey:String){
    private val apiClient = ApiClient.get()
    /**
     * @param phoneNumber
     */
    fun getAccountDetails(phoneNumber:String, listener: AccountListener){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val account =   apiClient.create(TransactionApi::class.java).getAccount(Model.AccountRequest(secretKey, phoneNumber)).await()
                listener.onSuccess(account)
            }catch (e:Exception){
                listener.onError(e.message!!)
            }
        }
    }

    /**
     * @param paymentRequest
      */
    fun pay(payment: PaymentRequest, listener: PaymentListener){

         GlobalScope.launch(Dispatchers.Main) {
             try {
                 apiClient.create(TransactionApi::class.java).transaction(payment).await()
                 listener.onSuccess()
             }catch (e:Exception){
                 listener.onError(error = e.message!!)
             }
         }

    }

}