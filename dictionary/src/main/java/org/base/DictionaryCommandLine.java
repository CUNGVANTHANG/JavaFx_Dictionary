package org.base;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class DictionaryCommandLine extends Dictionary {
    public static void showAllWords() {
        System.out.println("No   | English        | Vietnamese        ");
        int count = 1;

        // Get word from dictionary
        for (Word word : dictionary.values()) {
            System.out.printf("%-4d | %-14s | %s\n", count++,
                    word.getWord_target(), word.getWord_explain());
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static ArrayList<String> dictionarySearcher(String prefix) {
        ArrayList<String> resultList = new ArrayList<>();

        for (Map.Entry<String, Word> entry : dictionary.entrySet()) {
            String word = entry.getKey();
            if (word.startsWith(prefix)) {
                resultList.add(word);
            }
        }

        return resultList;
    }

    public static void dictionaryAdvanced() {
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");
        System.out.print("\nYour action: ");
        Scanner option = new Scanner(System.in);
        int options;
        do {
            String word_target;
            String word_explain;
            Scanner input = new Scanner(System.in);
            options = option.nextInt();
            switch (options) {
                case 0:
                    System.out.println("Exiting...");
                    options = -1;
                    break;
                case 1:
                    System.out.print("Enter word target: ");
                    word_target = input.nextLine();
                    System.out.print("Enter word explain: ");
                    word_explain = input.nextLine();
                    DictionaryManagement.addWord(word_target, word_explain);
                    break;
                case 2:
                    System.out.print("Enter word target: ");
                    word_target = input.nextLine();
                    DictionaryManagement.removeWord(word_target);
                    break;
                case 3:
                    System.out.print("Enter word target: ");
                    word_target = input.nextLine();
                    System.out.print("Enter word explain: ");
                    word_explain = input.nextLine();
                    DictionaryManagement.modifyWord(word_target, word_explain);
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    System.out.print("Enter the word you want to research: ");
                    word_target = input.nextLine();
                    DictionaryManagement.dictionaryLookup(word_target);
                    break;
                case 6:
                    System.out.print("Enter the word you want to research: ");
                    word_target = input.nextLine();
                    for (String word : dictionarySearcher(word_target)) {
                        System.out.println(word);
                    }
                    break;
                case 7:
                    // Gọi phương thức để chơi trò chơi
                    break;
                case 8:
                    DictionaryManagement.insertFromFile();
                    break;
                case 9:
                    DictionaryManagement.dictionaryExportToFile();
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
            System.out.println();
            System.out.println("[0] Exit     [1] Add       [2] Remove    [3] Update               [4] Display");
            System.out.println("[5] Lookup   [6] Search    [7] Game      [8] Import from file     [9] Export to file");
            System.out.print("\nYour action: ");
        } while (options != -1);

        option.close();
    }

    public static void main(String[] args) {
        dictionaryAdvanced();
    }
}