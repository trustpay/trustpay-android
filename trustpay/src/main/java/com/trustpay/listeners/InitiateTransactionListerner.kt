package com.trustpay.listeners

import com.trustpay.models.Model

interface InitiateTransactionListerner {
    fun onSucces(response:Model.InitiateTransactionResponse)
    fun onError(status:Int, message:String)
}