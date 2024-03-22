package org.base;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DictionaryManagement extends Dictionary {
    private static final String FILE_PATH = "/src/main/resources/databases/dictionaries.txt";
    private static final String SQLITE_PATH = "/src/main/resources/databases/dictionaries.db";

    public static String getAbsolutePath(String filePath) {
        // Get absolute path
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory = currentDirectory.replace("\\", "/");

        return currentDirectory + filePath;
    }

    public static void insertFromFile() {
        System.out.println("Loading from File...");
        try {
            // File Reader
            FileReader fileReader = new FileReader(getAbsolutePath(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // Add Words from File to the Dictionary - Complexity o(n)
            while ((line = bufferedReader.readLine()) != null) {
                String word_explain = "";
                String word_target = "";

                if (line.indexOf("|") != -1) {
                    word_target += line.substring(0, line.indexOf(" |"));
                    word_explain += line.substring(line.indexOf("|") + 2);
                    dictionary.put(word_target.toLowerCase(), new Word(word_target, word_explain));
                } else {
                    word_target += line;
                    dictionary.put(word_target.toLowerCase(), new Word(word_target, null));
                }
            }
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
        System.out.print("Enter on the keyboard the number of words (Word): ");
        int number_word = input.nextInt();
        input.nextLine();

        for (int i = 0; i < number_word; i++) {
            // Enter English - Vietnamese dictionary data on the keyboard
            System.out.print("Enter word target " + (i + 1) + ": ");
            String word_target = input.nextLine();
            System.out.print("Enter word explain " + (i + 1) + ": ");
            String word_explain = input.nextLine();

            // Add word to dictionary
            dictionary.put(word_target, new Word(word_explain, word_target));
        }

        input.close();
    }

    public static void insertFromSQLite() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + getAbsolutePath(SQLITE_PATH));

            if (connection != null) {
                System.out.println("Loading from SQLite...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM av");
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next() == true) {
                if (count == 25) {
                    break;
                }
                String word_target = resultSet.getString(2);
                String word_explain = resultSet.getString(3);
                dictionary.put(word_target, new Word(word_target, word_explain));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryExportToFile() {
        System.out.println("Export to File...");
        try {
            FileWriter fileWriter = new FileWriter(getAbsolutePath(FILE_PATH));
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Word word : dictionary.values()) {
                bufferedWriter.write(word.getWord_target() + " | " + word.getWord_explain() + "\n");
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

    public static void removeWord(String word_target) {
        if (dictionary.containsKey(word_target.toLowerCase())) {
            dictionary.remove(word_target.toLowerCase());
            System.out.println("Deleted words...");
        } else {
            System.out.println("Does not exist");
        }
    }

    public static void addWord(String word_target, String word_explain) {
        if (dictionary.containsKey(word_target.toLowerCase())) {
            System.out.println("Word exists");
        } else {
            dictionary.put(word_target.toLowerCase(), new Word(word_target, word_explain));
            System.out.println("Added words...");
        }
    }

    public static void modifyWord(String word_target, String word_explain) {
        if (dictionary.containsKey(word_target.toLowerCase())) {
            Word word = dictionary.get(word_target.toLowerCase());
            word.setWord_target(word_target);
            word.setWord_explain(word_explain);
            dictionary.put(word_target, word);
            System.out.println("Corrected words...");
        } else {
            System.out.println("Does not exist");
        }
    }

    public static void dictionaryLookup(String word_target) {
        Word word = dictionary.get(word_target.toLowerCase());
        if (word != null) {
            System.out.println(word.getWord_explain());
        } else {
            System.out.println("Does not exist");
        }
    }
}