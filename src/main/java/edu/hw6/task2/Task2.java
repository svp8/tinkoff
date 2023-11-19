package edu.hw6.task2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task2 {
    private Task2() {

    }

    public static Path cloneFile(Path path) throws IOException {
        Path createdPath = path;
        if (Files.exists(path)) {
            createdPath = getPath(path);
            Files.copy(path,createdPath);
        }
        return createdPath;
    }

    private static Path getPath(Path path) {
        Pattern pattern1 = Pattern.compile("(.*)\\.(?!.*\\.)(.*)$");
        Matcher matcher = pattern1.matcher(path.toString());
        String name;
        String extension;
        Path path1;
        if (matcher.matches()) {
            name = matcher.group(1);
            extension = matcher.group(2);
            path1 = Path.of(name + " — копия." + extension);
            int numberOfClone = 2;
            while (Files.exists(path1)) {
                path1 = Path.of(String.format("%s — копия (%d).%s", name, numberOfClone, extension));
                numberOfClone++;
            }
        } else { //Если без расширения
            name = path.toString();
            path1 = Path.of(name + " — копия");
            int numberOfClone = 2;
            while (Files.exists(path1)) {
                path1 = Path.of(name + " — копия (" + numberOfClone + ")");
            }
        }
        return path1;
    }
}
