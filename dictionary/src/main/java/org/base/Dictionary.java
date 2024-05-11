package org.base;

import java.util.TreeMap;

public class Dictionary {
    protected static TreeMap<String, Word> dictionary = new TreeMap<>();

    protected static String FILE_PATH = "/src/main/resources/databases/dictionaries.txt";
    protected static final String SQLITE_PATH = "/src/main/resources/databases/dictionaries.db";

}
