package com.trustpay

import com.trustpay.models.Model.Account
import com.trustpay.models.Model.AccountRequest
import com.trustpay.models.Model.PaymentRequest
import com.trustpay.models.Model.TransactionResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("check_account")
    fun getAccount(@Body request: AccountRequest):Deferred<Account>
    fun transaction(@Body request: PaymentRequest):Deferred<TransactionResponse>
}