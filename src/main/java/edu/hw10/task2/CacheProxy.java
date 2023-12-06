package edu.hw10.task2;

import java.io.FileWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CacheProxy {
    private CacheProxy() {
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public static <T> T create(T obj, Class<?> type) {
        Path path = Path.of("src/main/resources/cache");
        ClassLoader classLoader = type.getClassLoader();
        Class<?>[] interfaces = type.getInterfaces();
        InvocationHandler handler = (Object proxy, Method method, Object[] args) -> {
            Object result = method.invoke(obj, args);
            Annotation[] annotations = method.getAnnotations();
            Optional<Annotation> annotation =
                Arrays.stream(annotations).filter(x -> x.annotationType().equals(Cache.class)).findFirst();
            if (annotation.isPresent()) {
                Cache cache = (Cache) annotation.get();
                if (cache.persist()) {
                    if (!Files.exists(path)) {
                        Files.createFile(path);
                    }
                    try (FileWriter fileWriter = new FileWriter(path.toFile(), true)) {
                        fileWriter.write(method.getName() + " - " + Arrays.toString(args) + " = " + result + "\n");
                    }
                    LOGGER.info("Method invocation saved to: " + path);
                }
            }
            return result;
        };
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

}
