package org.base;

import java.io.*;

public class Bookmark extends DictionaryManagement {
    private static String FILE_PATH = "/src/main/resources/databases/bookmark.txt";

    public static void bookmarkExportToFile(String word_target, String word_explain) {
        try {
            FileWriter fileWriter = new FileWriter(DictionaryManagement.getAbsolutePath(FILE_PATH), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(word_target + " | " + word_explain + "\n");

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
        System.out.println(Colors.WHITE + "Loading from File...");
        try {
            // File Reader
            FileReader fileReader = new FileReader(getAbsolutePath(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

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
}
