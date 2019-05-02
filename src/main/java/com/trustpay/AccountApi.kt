package com.trustpay

import com.trustpay.models.Model.Account
import com.trustpay.models.Model.AccountRequest
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountApi {
    @POST("check_account")
    fun getAccount(@Body request: AccountRequest):Deferred<Account>
}