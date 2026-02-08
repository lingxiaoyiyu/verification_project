package com.api.basic.common;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JavaFileReader {
    public static List<String> readJavaFile(String filePath) throws Exception {
        return Files.readAllLines(Paths.get(filePath));
    }
}