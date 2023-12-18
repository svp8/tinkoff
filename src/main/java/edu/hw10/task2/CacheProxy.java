package edu.hw10.task2;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy {
    private final Path path;
    private final Map<String, Object> memoryCache;

    public Map<String, Object> getMemoryCache() {
        return Collections.unmodifiableMap(memoryCache);
    }

    public CacheProxy(Path path) throws IOException {
        this.path = path;
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        this.memoryCache = new HashMap<>();
    }

    public static final Logger LOGGER = LogManager.getLogger();

    public <T> T create(T obj, Class<?> type) {
        ClassLoader classLoader = type.getClassLoader();
        Class<?>[] interfaces = type.getInterfaces();
        InvocationHandler handler = new CacheHandler(obj, path, memoryCache);
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

}
