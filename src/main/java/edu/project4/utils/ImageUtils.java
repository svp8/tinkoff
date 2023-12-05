package edu.project4.utils;

import edu.project4.FlameImage;
import edu.project4.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ImageUtils {

    public static final int ALPHA = 200;

    private ImageUtils() {
    }

    private static final int A = 24;
    private static final int R = 16;
    private static final int G = 8;
    private static final Logger LOGGER = LogManager.getLogger();

    public static File createImageFile(FlameImage flameImage, String filename) throws IOException {
        int width = flameImage.getWidth();
        int height = flameImage.getHeight();
        Pixel[][] pixels = flameImage.getPixels();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Pixel currentPixel = pixels[i][j];
                int color =
                    (ALPHA << A) | (currentPixel.getColor().getRed() << R) | (currentPixel.getColor().getRed() << G)
                        | currentPixel.getColor().getBlue();
                bufferedImage.setRGB(j, i, color);
            }
        }
        // Save as PNG
        File file = new File("src/main/resources/" + filename + ".png");
        ImageIO.write(bufferedImage, "png", file);
        LOGGER.info("File created: " + file.getAbsolutePath());
        return file;
    }
}
