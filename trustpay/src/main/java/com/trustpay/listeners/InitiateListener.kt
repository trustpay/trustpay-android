package com.trustpay.listeners

import com.trustpay.models.Initiate

interface InitiateListener {
    fun onSuccess(response:Initiate.InitiateResponse)
    fun onError(statusCode:Int, message:String)
}