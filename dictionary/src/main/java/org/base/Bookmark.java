package org.base;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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

    public static String getFilePath() {
        return FILE_PATH;
    }

    public static void setFilePath(String filePath) {
        FILE_PATH = filePath;
    }

}
