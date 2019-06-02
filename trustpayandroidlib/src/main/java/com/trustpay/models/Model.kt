package com.trustpay.models

object Model{
    data class Account(val id:String, val name:String)
    data class AccountRequest(val secret_key:String, val phone_number:String)

    data class PaymentRequest(val secret_key: String,
                                  val id:String,
                                  val amount:Int,
                                  val account_pay:String,
                                  val ref:String,
                                  val pay_with:String,
                                  val status_pay:String,
                                  val message_pay:String ?=null,
                                  val device:String = "XAF",
                                  val description:String ?=null,
                                  val result_test:Boolean = true)

    data class TransactionResponse(val message:String)
}