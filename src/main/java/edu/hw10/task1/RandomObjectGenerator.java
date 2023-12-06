package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RandomObjectGenerator {
    Map<Class<?>, Constructor<?>> construtorMap;

    public RandomObjectGenerator(Map<Class<?>, Constructor<?>> construtorMap) {
        this.construtorMap = construtorMap;
    }

    public <T> T nextObject(Class<T> type, String methodName) throws NoSuchMethodException {
        T instance = null;
        Optional<Method> methodO =
            Arrays.stream(type.getMethods()).filter(x -> x.getName().equals(methodName)).findFirst();
        if (methodO.isEmpty()) {
            throw new NoSuchMethodException();
        }
        Method method = methodO.get();
        Parameter[] params = method.getParameters();
        Object[] vals = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i].getType().equals(int.class)) {
                vals[i] = checkForMinMax(params[i].getAnnotations());
            } else if (params[i].getType().equals(String.class)) {
                vals[i] = checkForNull(params[i].getAnnotations());
            }
        }
        try {
            instance = (T) method.invoke(null, vals);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public <T> T nextObject(Class<T> type) {
        T instance = null;
        Constructor<?> constructor = construtorMap.get(type);
        Parameter[] params = constructor.getParameters();
        Object[] vals = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i].getType().equals(int.class)) {
                vals[i] = checkForMinMax(params[i].getAnnotations());
            } else if (params[i].getType().equals(String.class)) {
                vals[i] = checkForNull(params[i].getAnnotations());
            }
        }
        try {
            instance = (T) constructor.newInstance(vals);
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static String checkForNull(Annotation[] annotations) {
        String result = null;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(NotNull.class)) {
                result = "temp";
            }
        }
        return result;
    }

    public static int checkForMinMax(Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnno = (Max) annotation;
                max = maxAnno.number();
            } else if (annotation.annotationType().equals(Min.class)) {
                Min minAnno = (Min) annotation;
                min = minAnno.number();
            }
        }
        Random random = new Random();
        return random.nextInt(min, max);
    }

}

