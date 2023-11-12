package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task2 {
    private Task2() {

    }

    public static Path cloneFile(Path path) throws IOException {
        Pattern pattern1 = Pattern.compile("(.*)\\.(?!.*\\.)(.*)$");
        Matcher matcher = pattern1.matcher(path.toString());
        if (Files.exists(path)) {
            String name;
            String extension;
            if (matcher.matches()) {
                name = matcher.group(1);
                extension = matcher.group(2);
                Path path1 = Path.of(name + " — копия." + extension);
                int numberOfClone = 2;
                while (Files.exists(path1)) {
                    path1 = Path.of(String.format("%s — копия (%d).%s", name, numberOfClone, extension));
                    numberOfClone++;
                }
                return Files.createFile(path1);
            } else { //Если без расширения
                name = path.toString();
                Path path1 = Path.of(name + " — копия");
                int numberOfClone = 2;
                while (Files.exists(path1)) {
                    path1 = Path.of(name + " — копия (" + numberOfClone + ")");
                }
                return Files.createFile(path1);
            }
        } else {
            return Files.createFile(path);
        }
    }

}
