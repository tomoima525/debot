package com.tomoima.debot.strategy


import android.app.Activity
import com.tomoima.debot.annotation.DebotAnnotation
import java.lang.reflect.InvocationTargetException


class DebotCallActivityMethodStrategy
/**
 * Constructor. A parameter should be same as a method name you want to call from Activity
 * @param methodName method name to call
 */
(internal val methodName: String) : DebotStrategy() {

    override fun startAction(activity: Activity) {
        val clazz = activity.javaClass
        val methods = clazz.declaredMethods
        for (method in methods) {
            if (!method.isAnnotationPresent(DebotAnnotation::class.java)) continue
            val annotation = method.getAnnotation(DebotAnnotation::class.java)
            if (methodName == (annotation as DebotAnnotation).value) {
                try {
                    method.invoke(activity)
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                }

            }

        }
    }
}
