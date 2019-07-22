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
     lateinit var name:String
     lateinit var payers:List<CheckAccount.Payer>

     fun initiate( listener:InitiateListener){
         GlobalScope.launch(Dispatchers.IO){
             try{
                val  response = apiClient.create(TransactionApi::class.java).initiate(Initiate.InitiateRequest(secretKey, amount, reference)).await()
                 withContext(Dispatchers.Main){
                     listener.onSuccess(response)

                 }

             }catch (http:HttpException){
                 withContext(Dispatchers.Main){
                     listener.onError(http.code(), http.message())

                 }
             }catch (e:Exception){
                 withContext(Dispatchers.Main){
                     listener.onError(500,e.message.toString())
                 }
             }
         }
    }

     fun checkAccount(listener:CheckAccountListener){
         GlobalScope.launch(Dispatchers.IO){
                try {
                    val response = apiClient.create(TransactionApi::class.java)
                        .checkAccount(CheckAccount.CheckAccountRequest(key, phoneNumber)).await()
                    withContext(Dispatchers.Main){
                        listener.onSuccess(response)
                    }
                }catch (http:HttpException){
                    withContext(Dispatchers.Main){
                        listener.onError(http.code(), http.message())
                    }

                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        listener.onError(500, e.message!!)
                    }
                }
         }
    }

     fun pay(payer:String, listener: PaymentListener){
         GlobalScope.launch(Dispatchers.IO) {
             try {
                 apiClient.create(TransactionApi::class.java)
                            .pay(Pay.PayRequest(key, payer)).await()
                 withContext(Dispatchers.Main){
                     listener.onSuccess()
                 }
             }catch (http:HttpException){
                 withContext(Dispatchers.Main){
                     listener.onError(http.code(), http.message())
                 }
             }catch (e:Exception){
                 withContext(Dispatchers.Main){
                     listener.onError(500, e.message!!)
                 }

             }
         }



    }
    fun getAmount() : Int{
        return this.amount
    }





}