package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap extends AbstractMap<String, String> {
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
    public String remove(Object key) {
        List<String> fileContent = null;
        String value = null;
        try {
            fileContent = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < fileContent.size(); i++) {
            String keyTemp = fileContent.get(i).split(":")[0];
            if (keyTemp.equals(key)) {
                value=fileContent.get(i).split(":")[1];
                fileContent.remove(i);
                break;
            }
        }
        try {
            Files.write(file.toPath(), fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
    @Override
    public void clear() {
        try {
            Files.write(file.toPath(), new ArrayList<>(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {

        Set<Entry<String, String>> entrySet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(":");
                Entry<String, String> entry = Map.entry(temp[0], temp[1]);
                entrySet.add(entry);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entrySet;
    }

}
