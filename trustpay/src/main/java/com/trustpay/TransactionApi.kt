package com.trustpay

import com.trustpay.models.Model
import com.trustpay.models.Model.Account
import com.trustpay.models.Model.AccountRequest
import com.trustpay.models.Model.PaymentRequest
import com.trustpay.models.Model.TransactionResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("check_account")
    fun getAccountAsync(@Body request: AccountRequest):Deferred<Account>
    fun getAccount(@Body request: AccountRequest): Call<Account>
    @POST("initiate_transaction")
    fun initiateTransactionAsync(@Body request: Model.InitiateTransactionRequest):Deferred<Model.InitiateTransactionResponse>
    @POST("transaction")
    fun transactionAsync(@Body request: PaymentRequest):Deferred<TransactionResponse>
    fun transaction(@Body request: PaymentRequest):Call<TransactionResponse>

}