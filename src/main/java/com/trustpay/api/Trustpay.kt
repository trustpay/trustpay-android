package com.trustpay.api

import com.trustpay.AccountApi
import com.trustpay.ApiClient
import com.trustpay.listeners.AccountListener
import com.trustpay.models.Model
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
                val account =   apiClient.create(AccountApi::class.java).getAccount(Model.AccountRequest(secretKey, phoneNumber)).await()
                listener.onSuccess(account)
            }catch (e:Exception){
                listener.onError(e.message!!)
            }
        }
    }

     fun pay(){

     }

}