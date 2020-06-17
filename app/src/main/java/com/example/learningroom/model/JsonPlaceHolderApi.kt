package com.example.learningroom.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface JsonPlaceHolderApi {

    //https://api-customer.thaichana.com/shop/0001/     +shopId

    @GET("{shopId}")
    fun getMerchant(@Path(value = "shopId")shopId: String):Call<Merchant>



}

