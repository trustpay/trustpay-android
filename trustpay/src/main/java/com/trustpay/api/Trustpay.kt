package com.trustpay.api

import com.trustpay.ApiClient
import com.trustpay.TransactionApi
import com.trustpay.listeners.*
import com.trustpay.models.CheckAccount
import com.trustpay.models.Initiate
import com.trustpay.models.Pay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.Serializable

/**
 * @author Gildas Tema <gildastema3@gmail.com>
 */
class Trustpay(private val secretKey:String, private val amount:Int, private val reference:String): Serializable {
    private val apiClient = ApiClient.get()

     lateinit var key: String
     lateinit var phoneNumber: String

     fun initiate( listener:InitiateListener){
        var response:Initiate.InitiateResponse ?= null
        try {
            GlobalScope.launch(Dispatchers.IO){
               response = apiClient.create(TransactionApi::class.java).initiate(Initiate.InitiateRequest(secretKey, amount, reference))
            }
            listener.onSuccess(response!!)
        }catch (http:HttpException){
            listener.onError(http.code(), http.message())
        }catch (e:Exception){
            listener.onError(500, e.message!!)
        }
    }

     fun checkAccount(listener:CheckAccountListener){
        var response:CheckAccount.CheckAccountResponse ?=null
        try {
            GlobalScope.launch(Dispatchers.IO){
                response = apiClient.create(TransactionApi::class.java).checkAccount(CheckAccount.CheckAccountRequest(key, phoneNumber))
            }
            listener.onSuccess(response!!)
        }catch (http:HttpException){
            listener.onError(http.code(), http.message())
        }catch (e:Exception){
            listener.onError(500, e.message!!)
        }
    }

     fun pay(payer:String, listener:PayListener){
        try {
            GlobalScope.launch(Dispatchers.IO){
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