package edu.hw6.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Task3Test {
    Path root = Path.of(Paths.get("")
        .toAbsolutePath()
        .toString(), "/src/test/java/edu/hw6/task3");

    @Test
    void filterExtension() {
        AbstractFilter extension = new ExtensionFilter("java");
        DirectoryStream.Filter<Path> filter = extension;
        ArrayList<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals("Task3Test.java", paths.get(0).getFileName().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void filterMagicNumber() {

        AbstractFilter magicNumber = new MagicNumberFilter(0x89, (int) 'P', (int) 'N');
        DirectoryStream.Filter<Path> filter = magicNumber;
        ArrayList<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals("file3.png", paths.get(0).getFileName().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void filterRegularAndReadable() {
        AbstractFilter regularFile = Files::isRegularFile;
        AbstractFilter writable = new IsWritableFilter();
        DirectoryStream.Filter<Path> filter = regularFile.and(writable);
        ArrayList<Path> paths = new ArrayList<>();
        File notReadable = Path.of(root.toString(), "fileNotReadable").toFile();
        notReadable.setWritable(false);
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals(4, paths.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void filterIsSmaller() {
        AbstractFilter isSmaller = new IsSmallerFilter(500);
        DirectoryStream.Filter<Path> filter = isSmaller;
        ArrayList<Path> paths = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals(3, paths.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Should return all files and directories")
    void filterRegexpAll() {
        AbstractFilter regexp = new RegexpFilter(".*");
        DirectoryStream.Filter<Path> filter = regexp;
        ArrayList<Path> paths = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals(6, paths.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Should return files with name contains file")
    void filterRegexpFile() {
        AbstractFilter regexp = new RegexpFilter("file.*");
        DirectoryStream.Filter<Path> filter = regexp;
        ArrayList<Path> paths = new ArrayList<>();

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(root, filter)) {
            entries.forEach(paths::add);
            Assertions.assertEquals(4, paths.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
