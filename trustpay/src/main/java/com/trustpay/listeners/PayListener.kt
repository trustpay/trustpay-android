package com.trustpay.listeners

interface  PayListener{
    fun onSuccess()
    fun onError(statusCode:Int, message:String)
}