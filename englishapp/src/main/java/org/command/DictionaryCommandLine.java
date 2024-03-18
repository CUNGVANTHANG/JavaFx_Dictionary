package org.command;

public class DictionaryCommandLine {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandLine(DictionaryManagement dictionaryManagement) {
        this.dictionaryManagement = dictionaryManagement;
    }

    public void showAllWords() {
        System.out.println("No   | English        | Vietnamese        ");
        int count = 1;

        // Get word from dictionary
        for (Word word : dictionaryManagement.getDictionary().getWordsSorted()) {
            System.out.printf("%-4d | %-14s | %s\n", count++,
                    word.getWord_target(), word.getWord_explain());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }
}