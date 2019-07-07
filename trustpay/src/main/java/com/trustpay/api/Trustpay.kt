package com.trustpay.api

import com.trustpay.ApiClient
import com.trustpay.TransactionApi
import com.trustpay.listeners.*
import com.trustpay.models.CheckAccount
import com.trustpay.models.Initiate
import com.trustpay.models.Model
import com.trustpay.models.Model.PaymentRequest
import com.trustpay.models.Pay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

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


    suspend fun initiate(amount:Int, ref:String, listener:InitiateListener){
        var response:Initiate.InitiateResponse ?= null
        try {
            withContext(Dispatchers.IO){
               response = apiClient.create(TransactionApi::class.java).initiate(Initiate.InitiateRequest(secretKey, amount, ref))
            }
            listener.onSuccess(response!!)
        }catch (http:HttpException){
            listener.onError(http.code(), http.message())
        }catch (e:Exception){
            listener.onError(500, e.message!!)
        }
    }

    suspend fun checkAccount(key:String, phoneNumber:String, listener:CheckAccountListener){
        var response:CheckAccount.CheckAccountResponse ?=null
        try {
            withContext(Dispatchers.IO){
                response = apiClient.create(TransactionApi::class.java).checkAccount(CheckAccount.CheckAccountRequest(key, phoneNumber))
            }
            listener.onSuccess(response!!)
        }catch (http:HttpException){
            listener.onError(http.code(), http.message())
        }catch (e:Exception){
            listener.onError(500, e.message!!)
        }
    }

    suspend fun pay(key:String, payer:String, listener:PayListener){
        try {
            withContext(Dispatchers.IO){
                apiClient.create(TransactionApi::class.java).pay(Pay.PayRequest(key, payer))
            }
            listener.onSuccess()
        }catch (http:HttpException){
            listener.onError(http.code(), http.message())
        }catch (e:Exception){
            listener.onError(500, e.message!!)
        }

    }





}