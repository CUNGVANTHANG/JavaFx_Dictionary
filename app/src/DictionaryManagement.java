package app.src;

import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary dictionary;

    public DictionaryManagement() {
        this.dictionary = new Dictionary();
    }

    public void insertFromCommandline() {
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
            dictionary.addWord(word);
        }

        input.close();
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
