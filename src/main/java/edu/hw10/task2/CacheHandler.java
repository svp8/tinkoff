package edu.hw10.task2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheHandler implements InvocationHandler {
    private final Object target;
    private final Path path;
    private final Map<String, Object> memoryCache;
    public static final Logger LOGGER = LogManager.getLogger();

    public CacheHandler(Object target, Path path, Map<String, Object> memoryCache) {
        this.target = target;
        this.path = path;
        this.memoryCache = memoryCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[] annotations = method.getAnnotations();
        Optional<Annotation> annotation =
            Arrays.stream(annotations).filter(x -> x.annotationType().equals(Cache.class)).findFirst();
        Object result;
        if (annotation.isPresent()) {
            Cache cache = (Cache) annotation.get();
            if (cache.persist()) {
                Optional<Object> resultOptional = findInFile(method, args);
                if (resultOptional.isEmpty()) {
                    result = method.invoke(target, args);
                    try (FileWriter fileWriter = new FileWriter(path.toFile(), true)) {
                        fileWriter.write(method.getName() + ":" + Arrays.toString(args) + "=" + result + "\n");
                    }
                    LOGGER.info("Method invocation saved to: " + path);
                } else {
                    result = resultOptional.get();
                }
            } else {
                Optional<Object> resultOptional = findInMemory(method, args);
                if (resultOptional.isEmpty()) {
                    result = method.invoke(target, args);
                    String key = method.getName() + ":" + Arrays.toString(args);
                    memoryCache.put(key, result);
                } else {
                    result = resultOptional.get();
                }
            }
        } else {
            result = method.invoke(target, args);
        }
        return result;
    }

    private Optional<Object> findInMemory(Method method, Object[] args) {
        String key = method.getName() + ":" + Arrays.toString(args);
        if (memoryCache.containsKey(key)) {
            return Optional.of(memoryCache.get(key));
        }
        return Optional.empty();
    }

    private Optional<Object> findInFile(Method method, Object[] args) {
        Class<?> returnType = method.getReturnType();
        if (!returnType.equals(int.class) && !returnType.equals(long.class)
            && !returnType.equals(String.class)) {
            throw new IllegalArgumentException("Return type can not be persisted: only int, long or String");
        }
        String key = method.getName() + ":" + Arrays.toString(args);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] input = line.split("=");
                if (input[0].equals(key)) {
                    if (returnType.equals(int.class)) {
                        return Optional.of(Integer.parseInt(input[1]));
                    } else if (returnType.equals(long.class)) {
                        return Optional.of(Long.parseLong(input[1]));
                    } else if (returnType.equals(String.class)) {
                        return Optional.of(Long.parseLong(input[1]));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
