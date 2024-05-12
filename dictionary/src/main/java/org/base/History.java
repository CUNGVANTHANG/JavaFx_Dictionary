package org.base;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Local: cungvanthang main

public class History extends DictionaryManagement {
    protected static String FILE_PATH = "/src/main/resources/databases/history.txt";

    public static void insertFromFile() {
        insertFromFile(FILE_PATH);
    }

    public static void addWord(String word_target, String word_explain) {
        ArrayList<String> history = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(DictionaryManagement.getAbsolutePath(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                history.add(line);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(DictionaryManagement.getAbsolutePath(FILE_PATH));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < history.size(); i++) {
                if (history.get(i).substring(0, history.get(i).indexOf(" | ")).trim().equals(word_target)) {
                    history.remove(i);
                }
            }

            history.add(word_target + " | " + word_explain);

            for (String word : history) {
                bufferedWriter.write(word + "\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeWord(String word_target) {
        if (dictionary.containsKey(word_target.toLowerCase())) {
            dictionary.remove(word_target.toLowerCase());
            System.out.println(Colors.WHITE + "Deleted words...");
        } else {
            System.out.println(Colors.RED + "Does not exist");
        }

        dictionaryExportToFile();
    }

    public static void dictionaryExportToFile() {
        dictionaryExportToFile(FILE_PATH);
    }

    public static ArrayList<String> showHistory() {
        ArrayList<String> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(getAbsolutePath(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line.substring(0, line.indexOf(" | ")).trim());
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(result);

        return result;
    }
}
