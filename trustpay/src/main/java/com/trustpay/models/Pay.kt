package com.trustpay.models

import com.google.gson.annotations.SerializedName

object Pay {

    data class PayRequest(@SerializedName("temporal_key") val key:String,
                          @SerializedName("payer_id") val payer:String)


}