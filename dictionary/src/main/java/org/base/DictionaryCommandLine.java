package org.base;

import java.util.Scanner;

public class DictionaryCommandLine extends Dictionary {
    public static void showAllWords() {
        System.out.println(Colors.YELLOW + "No   | English        | Vietnamese        ");
        int count = 1;

        // Get word from dictionary
        for (Word word : dictionary.values()) {
            System.out.printf(Colors.YELLOW + "%-4d | %-14s | %s\n", count++,
                    word.getWord_target(), word.getWord_explain());
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() {
        System.out.println(Colors.BLUE + "Welcome to My Application!");
        System.out.println(Colors.BLUE + "[0] Exit");
        System.out.println(Colors.BLUE + "[1] Add");
        System.out.println(Colors.BLUE + "[2] Remove");
        System.out.println(Colors.BLUE + "[3] Update");
        System.out.println(Colors.BLUE + "[4] Display");
        System.out.println(Colors.BLUE + "[5] Lookup");
        System.out.println(Colors.BLUE + "[6] Search");
        System.out.println(Colors.BLUE + "[7] Game");
        System.out.println(Colors.BLUE + "[8] Import from file");
        System.out.println(Colors.BLUE + "[9] Export to file");
        System.out.print(Colors.BLUE + "\nYour action: ");
        Scanner option = new Scanner(System.in);
        int options;
        do {
            String word_target;
            String word_target_modify;
            String word_explain;
            Scanner input = new Scanner(System.in);
            options = option.nextInt();
            switch (options) {
                case 0:
                    System.out.println(Colors.WHITE + "Exiting...");
                    options = -1;
                    break;
                case 1:
                    System.out.print(Colors.RED + "Enter word target: ");
                    word_target = input.nextLine();
                    System.out.print(Colors.RED + "Enter word explain: ");
                    word_explain = input.nextLine();
                    DictionaryManagement.addWord(word_target, word_explain);
                    break;
                case 2:
                    System.out.print(Colors.RED + "Enter word target: ");
                    word_target = input.nextLine();
                    DictionaryManagement.removeWord(word_target);
                    break;
                case 3:
                    System.out.print(Colors.RED + "Enter word target: ");
                    word_target = input.nextLine();
                    System.out.print(Colors.RED + "Enter word target: ");
                    word_target_modify = input.nextLine();
                    System.out.print(Colors.RED + "Enter word explain: ");
                    word_explain = input.nextLine();
                    DictionaryManagement.modifyWord(word_target, word_target_modify, word_explain);
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    System.out.print(Colors.RED + "Enter the word you want to lookup: ");
                    word_target = input.nextLine();
                    System.out.println(Colors.YELLOW + DictionaryManagement.dictionaryLookup(word_target));
                    break;
                case 6:
                    System.out.print(Colors.RED + "Enter the word you want to research: ");
                    word_target = input.nextLine();
                    for (String word : DictionaryManagement.dictionarySearcher(word_target)) {
                        System.out.println(Colors.YELLOW + word);
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
                    System.out.println(Colors.RED + "Invalid option. Please choose again.");
            }

            if (options != -1) {
                System.out.println();
                System.out.println(Colors.BLUE + "[0] Exit     [1] Add       [2] Remove    [3] Update               [4] Display");
                System.out.println(Colors.BLUE + "[5] Lookup   [6] Search    [7] Game      [8] Import from file     [9] Export to file");
                System.out.print(Colors.BLUE + "\nYour action: ");
            }
        } while (options != -1);

        option.close();
    }

    public static void main(String[] args) {
        dictionaryAdvanced();
    }
}