package edu.project4.utils;

import edu.project4.FlameImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.jupiter.api.Assertions.*;

class ImageUtilsTest {

    @Test
    void createImageFile() throws IOException {
        FlameImage flameImage=new FlameImage(10,10);

        File file=ImageUtils.createImageFile(flameImage,"test");

        Assertions.assertTrue(Files.exists(file.toPath()));
        Assertions.assertTrue(file.toPath().endsWith("test.png"));
        Files.delete(file.toPath());
    }
}
