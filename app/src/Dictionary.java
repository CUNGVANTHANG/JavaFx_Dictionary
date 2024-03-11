package app.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Dictionary {
    private ArrayList<Word> words;

    public Dictionary() {
        this.words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }

    // Sort dictionary list by English vocabulary (word_target)
    public ArrayList<Word> getWordsSorted() {
        Collections.sort(words, Comparator.comparing(Word::getWord_target));
        return words;
    }
}
