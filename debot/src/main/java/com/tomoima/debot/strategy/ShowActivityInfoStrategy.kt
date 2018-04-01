package com.tomoima.debot.strategy

import android.app.Activity
import android.content.Intent
import com.tomoima.debot.util.DialogUtil
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class ShowActivityInfoStrategy : DebotStrategy() {
    override fun startAction(activity: Activity) {
        DialogUtil.showDialog(activity, activity.javaClass.simpleName, getJSONObject(activity.intent).toString())
    }


    private fun getJSONObject(intent: Intent): JSONObject {
        val data = JSONObject()
        val bundle = intent.extras ?: return data

        for (key in bundle.keySet()) {
            val value = bundle.get(key)
            if (value is String) {
                val str = value.toString()
                try {
                    val jsonArray = JSONArray(str)
                    put(data, key, jsonArray)
                } catch (e1: JSONException) {
                    put(data, key, str)
                }
            } else {
                put(data, key, value)
            }
        }
        return data
    }

    private fun put(obj: JSONObject, key: String, value: Any) {
        try {
            obj.put(key, value)
        } catch (e: JSONException) {
            throw RuntimeException()
        }

    }


}
