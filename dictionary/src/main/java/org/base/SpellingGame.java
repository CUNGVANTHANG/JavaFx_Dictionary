package org.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpellingGame extends Game{
    public void insertFromFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
