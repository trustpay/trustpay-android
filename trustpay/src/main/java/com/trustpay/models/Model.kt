package com.trustpay.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

object Model{
    data class Account(val id:String, val name:String)
    data class AccountRequest(val secret_key:String, val phone_number:String)

    data class PaymentRequest(@SerializedName("secret_key") val secretKey: String,
                              val id:String,
                              val amount:Int,
                              @SerializedName("account_pay") val accountPay:String,
                              @SerializedName("pay_with") val payWith:String,
                              @SerializedName("status_pay") val statusPay:String?=null,
                              @SerializedName("message_pay") val messagePay:String ?=null,
                              @SerializedName("result_test") val resultTest:Boolean = true)

    data class TransactionResponse(val message:String)

    data class InitiateTransactionRequest(@SerializedName("secret_key") val secretKey:String,
                                          @SerializedName("phone_number") val phoneNumber:String,
                                          @SerializedName("amount") val amount: Int,
                                          @SerializedName("ref") val ref: String,
                                          @SerializedName("description") val description: String?=null)


    data class InitiateTransactionResponse(@SerializedName("name") val name:String, val id: String,
                                           @SerializedName("payers") val payers:List<Payer>): Serializable

    data class Payer(@SerializedName("payer_id") val id: String,
                     @SerializedName("name") val name: String,
                     @SerializedName("status") val status:Int)
}