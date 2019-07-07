package com.trustpay.listeners

import com.trustpay.models.CheckAccount

interface CheckAccountListener {
    fun onSuccess(response:CheckAccount.CheckAccountResponse)
    fun onError(statusCode:Int, message:String)
}