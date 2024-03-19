package org.base;

public class DictionaryCommandLine extends Dictionary {
    public static void showAllWords() {
        System.out.println("No   | English        | Vietnamese        ");
        int count = 1;

        DictionaryManagement.handleSortWords();
        // Get word from dictionary
        for (Word word : dictionary) {
            System.out.printf("%-4d | %-14s | %s\n", count++,
                    word.getWord_target(), word.getWord_explain());
        }
    }

    public static void main(String[] args) {
        DictionaryManagement.insertFromFile();
        System.out.println(dictionary.size());
        DictionaryManagement.addWords("bully", null);
    }
}
