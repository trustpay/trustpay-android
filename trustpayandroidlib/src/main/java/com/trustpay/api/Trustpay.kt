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
    fun getAccountDetailsAsync(phoneNumber:String, listener: AccountListener){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val account =   apiClient.create(TransactionApi::class.java).getAccountAsync(Model.AccountRequest(secretKey, phoneNumber)).await()
                listener.onSuccess(account)
            }catch (e:Exception){
                listener.onError(e.message!!)
            }
        }
    }

    /**
     * @param phoneNumber
     */
    fun getAccountDetails(phoneNumber: String): Response<Model.Account> {
        return   apiClient.create(TransactionApi::class.java).getAccount(Model.AccountRequest(secretKey, phoneNumber)).execute()
    }

    /**
     * @param paymentRequest
      */
    fun payAsync(payment: PaymentRequest, listener: PaymentListener){

         GlobalScope.launch(Dispatchers.Main) {
             try {
                 apiClient.create(TransactionApi::class.java).transactionAsync(payment).await()
                 listener.onSuccess()
             }catch (http:HttpException){
                 listener.onError(http.code(), http.message())
             }
             catch (e:Exception){
                 listener.onError(500, e.message!!)
             }
         }

    }

    /**
     * @param payment
     */
    fun pay(payment:PaymentRequest): Response<TransactionResponse> {
        return apiClient.create(TransactionApi::class.java).transaction(payment).execute()
    }


    fun initiateTransactionAsync(request:Model.InitiateTransactionRequest, listener:InitiateTransactionListerner){
        GlobalScope.launch(Dispatchers.Main) {
            try{
                val response = apiClient.create(TransactionApi::class.java).initiateTransactionAsync(request).await()
                listener.OnSuccess(response)
            }catch (http:HttpException){
                listener.onError(http.code(), http.message())
            }catch (e:Exception){
                listener.onError(500, e.message!!)
            }
        }
    }





}