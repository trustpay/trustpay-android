package com.trustpay

import com.trustpay.models.CheckAccount
import com.trustpay.models.Initiate
import com.trustpay.models.Model
import com.trustpay.models.Model.Account
import com.trustpay.models.Model.AccountRequest
import com.trustpay.models.Model.PaymentRequest
import com.trustpay.models.Model.TransactionResponse
import com.trustpay.models.Pay
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("check_account")
    fun getAccountAsync(@Body request: AccountRequest):Deferred<Account>
    suspend fun getAccount(@Body request: AccountRequest): Account
    @POST("initiate_transaction")
    fun initiateTransactionAsync(@Body request: Model.InitiateTransactionRequest):Deferred<Model.InitiateTransactionResponse>
    @POST("initiate_transaction")
    suspend fun initiateTransaction(@Body request: Model.InitiateTransactionRequest):Model.InitiateTransactionResponse
    @POST("transaction")
    fun transactionAsync(@Body request: PaymentRequest):Deferred<TransactionResponse>
    @POST("transaction")
    suspend fun transaction(@Body request: PaymentRequest):TransactionResponse


    @POST("initiate")
    fun initiate(@Body request:Initiate.InitiateRequest): Initiate.InitiateResponse

    fun checkAccount(@Body request:CheckAccount.CheckAccountRequest): CheckAccount.CheckAccountResponse

    fun pay(@Body request:Pay.PayRequest): Void

}