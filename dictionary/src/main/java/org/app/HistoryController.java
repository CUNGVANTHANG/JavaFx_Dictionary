package org.app;

import javafx.fxml.Initializable;
import org.base.Word;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController extends GeneralController implements Initializable  {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        importSearchHistory();
    }

    public void importSearchHistory() {
        System.out.println(colors.WHITE + "Loading from File...");

        try {
            FileReader fileReader = new FileReader(dictionaryManagement.getAbsolutePath(FILE_HISTORY_PATH));
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                searchHistory.add(line);
            }

            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportSearchHistory(String result) {
        searchHistory.add(result);
    }

}
