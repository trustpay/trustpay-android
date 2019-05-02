package com.trustpay.listeners

interface PaymentListener{
    fun onSuccess()
    fun onError(error:String)
}