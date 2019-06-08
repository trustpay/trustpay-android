package com.trustpay.listeners

import com.trustpay.models.Model

interface InitiateTransactionListerner {
    fun OnSuccess( response:Model.InitiateTransactionResponse)
    fun onError(status:Int, message:String)
}