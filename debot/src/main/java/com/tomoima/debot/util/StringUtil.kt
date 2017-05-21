package com.tomoima.debot.util

internal object StringUtil {
    fun isBlankOrNull(str: String?): Boolean {
        return str == null || str.isEmpty()
    }
}
