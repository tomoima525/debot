package com.tomoima.debot.strategy;


import android.app.Activity;
import android.support.annotation.NonNull;

import com.tomoima.debot.annotation.DebotAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class DebugInputStrategy extends DebotStrategy{

    final String methodName;

    public DebugInputStrategy(@NonNull String methodName){
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
                    method.invoke(activity, null);
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
