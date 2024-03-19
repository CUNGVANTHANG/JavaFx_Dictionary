package org.base;

import java.io.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    // File Path
    public static final String FILE_PATH = "/src/main/resources/databases/test.txt";

    // Handle Function
    public static String getAbsolutePath(String filePath) {
        // Get absolute path
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory = currentDirectory.replace("\\", "/");

        return currentDirectory + filePath;
    }

    public static void handleSortWords() {
        Collections.sort(dictionary, Comparator.comparing(Word::getWord_target));
    }

    // Method
    public static void dictionaryExportToFile() {
        try {
            FileWriter fileWriter = new FileWriter(getAbsolutePath(FILE_PATH));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Word word : dictionary) {
                bufferedWriter.write("@" + word.getWord_target() + " " + word.getWord_explain() + "\n");
            }

            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertFromFile() {
        try {
            // File Reader
            FileReader fileReader = new FileReader(getAbsolutePath(FILE_PATH));
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
                         word_target += line.substring(1, line.indexOf(" /"));
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

    public static void addWords(String word_target, String word_explain) {
        // Convert to Lower case
        word_target = word_target.toLowerCase();

        // Get Index word_target
        int index = Collections.binarySearch(dictionary,
                new Word(word_target, null), Comparator.comparing(Word::getWord_target));

        // Check Exist
        if (index >= 0) {
            System.out.println("Word exists");
            return;
        } else {

        }

        // Update Word to File

    }

    public static void modifyWord(String word_target, String word_explain) {

    }

    public static void removeWord(String word_target) {
        // Convert to Lower case
        word_target = word_target.toLowerCase();

        // Get Index word_target
        int index = Collections.binarySearch(dictionary,
                new Word(word_target, null), Comparator.comparing(Word::getWord_target));

        // Check Exist
        if (index >= 0) {
            dictionary.remove(dictionary.get(index));
        } else {
            System.out.println("Does not exist");
        }

        // Update Word to File
        dictionaryExportToFile();
    }

    // Lookup Dictionary
    public static void dictionaryLookup() {
        Scanner input = new Scanner(System.in);

        input.close();
    }


}
