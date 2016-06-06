package com.tomoima.debot.strategy;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.tomoima.debot.annotation.DebotAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class DebotCallActivityMethodStrategy extends DebotStrategy{

    final String methodName;

    /**
     * Constructor. A parameter should be same as a method name you want to call from Activity
     * @param methodName method name to call
     */
    public DebotCallActivityMethodStrategy(@NonNull String methodName){
        this.methodName = methodName;
    }

    @Override
    public void startAction(@NonNull Activity activity) {
        Class clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (!method.isAnnotationPresent(DebotAnnotation.class)) continue;
            Annotation annotation = method.getAnnotation(DebotAnnotation.class);
            if (methodName.equals(((DebotAnnotation) annotation).value())){
                try {
                    method.invoke(activity);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
