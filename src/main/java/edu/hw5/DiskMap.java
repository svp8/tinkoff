package edu.hw5;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

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
        return 0;
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
                System.out.println(line);
                String tempKey = line.split(":")[0];
                if (tempKey.equals(key)) {
                    found = true;
                }
                line = reader.readLine();
            }
            reader.close();
            return found;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public String get(Object key) {
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if(containsKey(key)){
                bw.write(key + ":" + value)
            }
            bw.write(key + ":" + value);
            bw.newLine();
            bw.close();
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
