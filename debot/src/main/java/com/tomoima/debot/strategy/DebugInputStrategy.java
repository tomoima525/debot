package com.tomoima.debot.strategy;


//This class is still under construction
//public class DebugInputStrategy extends DebotStrategy{
//    final static String TAG = DebugInputStrategy.class.getSimpleName();
//    @Override
//    public void startAction(@NonNull Activity activity) {
//        Class clazz = Activity.class;
//        Method[] methods = clazz.getDeclaredMethods();
//        Annotation[] annotations = methods[0].getDeclaredAnnotations();
//        for (Annotation annotation : annotations) {
//            Log.v(TAG, "Annotation " + annotation.toString());
//            if(annotation instanceof DebotAnnotation){
//                String methodName = ((DebotAnnotation)annotation).value();
//                try {
//                    Method method = clazz.getClass().getMethod(methodName, null);
//                    method.invoke(clazz, null);
//                } catch (NoSuchMethodException e) {
//                    throw new RuntimeException("There is no such method named " + methodName);
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
