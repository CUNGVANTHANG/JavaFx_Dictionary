package org.base;

import java.util.TreeMap;

public class Dictionary {
    // Remove, Add, Search -> 0(log n)
    protected static TreeMap<String, Word> dictionary = new TreeMap<>();

    private static String FILE_PATH = "/src/main/resources/databases/dictionaries.txt";

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }
}
