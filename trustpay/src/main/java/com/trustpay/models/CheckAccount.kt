package com.trustpay.models

import com.google.gson.annotations.SerializedName

object CheckAccount {

    data class CheckAccountRequest(@SerializedName("temporal_key") val key:String,
                                   @SerializedName("account") val phoneNumber:String)

    data class CheckAccountResponse(val name:String , val payers:List<Payer>)

    data class Payer(@SerializedName("payer_id") val payerId:String, val  name:String)
}