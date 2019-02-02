package com.cenock.reigntest.hnmobiletest.data.network

import android.util.Log
import retrofit2.Call
import java.io.IOException

abstract class Callback <T> : retrofit2.Callback<T> {

    private val tag = Callback::class.java.simpleName

    override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
        if (response != null) {

            if (response.isSuccessful) {
                onSuccess(response.body())
            } else {
                var json: String? = null
                val errorBody = response.errorBody()
                try {
                    json = errorBody!!.string()
                } catch (e: IOException) {
                    Log.e(tag, e.toString())
                }

                val code = response.code()
                val message =
                    Utils.getBodyErrorMessage(errorBody, json!!)

                onError(code, message)
            }
        } else {
            onFailure(null)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure(t)
    }

    abstract fun onSuccess(response: T?)

    abstract fun onError(code: Int, error: String?)

    abstract fun onFailure(t: Throwable?)



}