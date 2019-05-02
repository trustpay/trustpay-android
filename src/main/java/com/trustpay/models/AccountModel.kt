package com.trustpay.models

object Model{
    data class Account(val id:String, val name:String)
    data class AccountRequest(val secret_key:String, val phone_number:String)
}