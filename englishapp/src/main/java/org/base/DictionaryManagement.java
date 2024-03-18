package org.base;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    // File Path
    public static final String INPUT_PATH = "/src/main/resources/databases/test.txt";
    public static final String OUTPUT_PATH = "/src/main/resources/databases/test_update.txt";

    // Handle Function
    public static String getAbsolutePath(String filePath) {
        // Get absolute path
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory = currentDirectory.replace("\\", "/");

        return currentDirectory + filePath;
    }

    // File .txt Import
    public static void insertFromFile() {
        try {
            // File Reader
            FileReader fileReader = new FileReader(getAbsolutePath(INPUT_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String word_explain = "";
            String word_target = "";

            // Add Words from File to the Dictionary - Complexity o(n)
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) {
                    Word word = new Word(word_target, word_explain);
                    dictionary.add(word);
                    word_target = "";
                    word_explain = "";
                    continue;
                }
                if (line.startsWith("@")) {
                     if (line.indexOf("/") != -1) {
                         word_target += line.substring(1, line.indexOf(" /")) + "\n";
                         word_explain += line.substring(line.indexOf("/")) + "\n";
                     } else {
                         word_target += line;
                     }
                } else {
                    word_explain += line + "\n";
                }
            }
            Word word = new Word(word_target, word_explain);
            dictionary.add(word);

            // File Close
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Commandline Import
    public static void insertFromCommandline() {
        Scanner input = new Scanner(System.in);

        // Enter on the keyboard the number of words
        int number_word = input.nextInt();
        input.nextLine();

        for (int i = 0; i < number_word; i++) {
            // Enter English - Vietnamese dictionary data on the keyboard
            String word_explain = input.nextLine();
            String word_target = input.nextLine();

            // Add word to dictionary
            Word word = new Word(word_explain, word_target);
            dictionary.add(word);
        }

        input.close();
    }

    // Dictionary Export to File
    public static void dictionaryExportToFile() {

    }

    // Lookup Dictionary
    public static void dictionaryLookup() {

    }

    // Add Words to Dictionary
    public static void addWords(String word_target, String word_explain) {

    }

    public static void modifyWord(String word_target, String word_explain) {

    }

    public static void removeWord(String word_target) {
        // Convert to lower case
        word_target = word_target.toLowerCase();

        // Get index word_target

    }

    public static void main(String[] args) {

    }
}
