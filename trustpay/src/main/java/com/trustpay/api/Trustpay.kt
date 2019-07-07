package com.trustpay.api

import com.trustpay.ApiClient
import com.trustpay.TransactionApi
import com.trustpay.listeners.AccountListener
import com.trustpay.listeners.InitiateTransactionListerner
import com.trustpay.listeners.PaymentListener
import com.trustpay.models.Model
import com.trustpay.models.Model.PaymentRequest
import com.trustpay.models.Model.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author Gildas Tema <gildastema3@gmail.com>
 */
class Trustpay(private val secretKey:String){
    private val apiClient = ApiClient.get()
    /**
     * @param phoneNumber
     */
    fun getAccountDetails(phoneNumber:String, listener: AccountListener){
        GlobalScope.launch(Dispatchers.IO){
            try {
                val account =   apiClient.create(TransactionApi::class.java).getAccount(Model.AccountRequest(secretKey, phoneNumber))
                listener.onSuccess(account)
            }catch (e:Exception){
                listener.onError(e.message!!)
            }
        }
    }



    /**
     * @param paymentRequest
      */
    fun payAsync(payment: PaymentRequest, listener: PaymentListener){

         GlobalScope.launch(Dispatchers.IO) {
             try {
                 apiClient.create(TransactionApi::class.java).transaction(payment)
                 listener.onSuccess()
             }catch (http:HttpException){
                 listener.onError(http.code(), http.message())
             }
             catch (e:Exception){
                 listener.onError(500, e.message!!)
             }
         }

    }




    fun initiateTransactionAsync(request:Model.InitiateTransactionRequest, listener:InitiateTransactionListerner){
        GlobalScope.launch(Dispatchers.IO) {
            try{
                val response = apiClient.create(TransactionApi::class.java).initiateTransaction(request)
                listener.onSucces(response)
            }catch (http:HttpException){
                listener.onError(http.code(), http.message())
            }catch (e:Exception){
                listener.onError(500, e.message!!)
            }
        }
    }





}