package com.cenock.reigntest.hnmobiletest.data.network

import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject

object Utils {
    private val TAG = Utils::class.java.simpleName

    private const val MESSAGE = "status_message"

    fun getBodyErrorMessage(errorBody: ResponseBody?, json: String): String? {
        if (errorBody != null) {
            try {
                val errorJSON = JSONObject(json)
                return errorJSON.getString(MESSAGE)
            } catch (e: JSONException) {
                Log.e(TAG, e.toString())
            }

            return null
        } else {
            return null
        }
    }
}