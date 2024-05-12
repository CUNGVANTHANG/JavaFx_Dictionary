package org.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuizGame extends Game {
    private static String FILE_PATH = "/src/main/resources/databases/quizGame.txt";
    public static void insertFromFile() {
        try {
            FileReader fileReader = new FileReader(DictionaryManagement.getAbsolutePath(FILE_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                ArrayList<String> answer = new ArrayList<>();
                Question question = new Question(parts[0].trim(), answer, parts[parts.length - 1].trim());
                for (int i = 1; i < parts.length - 1; i++) {
                    answer.add(parts[i].trim());
                }
                game.add(question);
            }

            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
