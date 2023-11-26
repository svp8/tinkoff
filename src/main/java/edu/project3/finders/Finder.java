package edu.project3.finders;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Finder {
    Map<String, List<String>> find(String path, Path root);
}
