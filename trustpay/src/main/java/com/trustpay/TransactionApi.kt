package com.trustpay

import com.trustpay.models.CheckAccount
import com.trustpay.models.Initiate
import com.trustpay.models.Pay
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("initiate")
    fun initiate(@Body request:Initiate.InitiateRequest):Deferred<Initiate.InitiateResponse>
    @POST("check_account")
    fun checkAccount(@Body request:CheckAccount.CheckAccountRequest): Deferred<CheckAccount.CheckAccountResponse>
    @POST("pay")
    fun pay(@Body request:Pay.PayRequest): Deferred<Unit>

}