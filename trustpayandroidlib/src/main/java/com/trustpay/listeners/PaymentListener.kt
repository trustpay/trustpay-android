package com.trustpay.listeners

interface PaymentListener{
    fun onSuccess()
    fun onError(status:Int, message: String)
}