package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    File file;

    public DiskMap(Path path) throws IOException {
        if (Files.exists(path)) {
            file = path.toFile();
        } else {
            Path path1 = Files.createFile(path);
            file = path1.toFile();
        }

    }

    @Override
    public int size() {
        List<String> fileContent;
        try {
            fileContent = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            return fileContent.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        BufferedReader reader;
        boolean found = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String tempKey = line.split(":")[0];
                if (tempKey.equals(key)) {
                    found = true;
                }
                line = reader.readLine();
            }
            reader.close();
            return found;
        } catch (IOException ignored) {
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public String get(Object key) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(":");
                if (temp[0].equals(key)) {
                    reader.close();
                    return temp[1];
                }
                line = reader.readLine();
            }
        } catch (IOException ignored) {
        }
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        List<String> fileContent = null;
        boolean found = false;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < fileContent.size(); i++) {
            String keyTemp = fileContent.get(i).split(":")[0];
            if (keyTemp.equals(key)) {
                found = true;
                fileContent.set(i, key + ":" + value);
                break;
            }
        }
        if (!found) {
            fileContent.add(key + ":" + value);
        }
        try {
            Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return key;
    }

    @Override
    public String remove(Object key) {
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return null;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }
}
