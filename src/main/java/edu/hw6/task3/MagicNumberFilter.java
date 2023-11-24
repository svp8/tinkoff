package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class MagicNumberFilter implements AbstractFilter {
    ArrayList<Integer> numbers = new ArrayList<>();

    public MagicNumberFilter(Integer... numbers) {
        this.numbers.addAll(Arrays.asList(numbers));
    }

    @Override
    public boolean accept(Path entry) throws IOException {
        if (!Files.isRegularFile(entry)) {
            return false;
        }
        byte[] bytes = Files.readAllBytes(entry);
        int i = 0;
        if (bytes.length == 0) {
            return false;
        }
        for (Integer magicNumber : numbers) {
            if (i == bytes.length) {
                break;
            }
            if (magicNumber != Byte.toUnsignedInt(bytes[i])) {
                return false;
            }
            i++;
        }
        return i == numbers.size();
    }
}
