package com.trustpay.models

import com.google.gson.annotations.SerializedName

object Initiate {
    data class InitiateRequest(@SerializedName("secret_key")  val secretKey:String,
                               val amount:Int,
                               val ref:String)

    data class InitiateResponse(@SerializedName("temporal_key") val key:String)
}