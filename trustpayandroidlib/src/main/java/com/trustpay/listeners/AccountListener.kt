package com.trustpay.listeners

import com.trustpay.models.Model.Account

interface AccountListener{
    fun onSuccess(account: Account)
    fun onError(error:String)
}