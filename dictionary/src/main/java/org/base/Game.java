package org.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private String question;
    private ArrayList answer = new ArrayList();
    private String correctAnswer;

    public ArrayList getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("programming");
        words.add("java");
        words.add("computer");
        words.add("algorithm");
        words.add("software");

        // Trộn ngẫu nhiên các từ trong danh sách
        Collections.shuffle(words);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Sorting Game!");
        System.out.println("You need to rearrange the letters to form meaningful words.");

        for (String word : words) {
            // Chuyển từng từ thành mảng các ký tự để trộn ngẫu nhiên
            char[] characters = word.toCharArray();
            ArrayList<Character> shuffledCharacters = new ArrayList<>();
            for (char character : characters) {
                shuffledCharacters.add(character);
            }
            Collections.shuffle(shuffledCharacters);

            // In ra từ đã trộn ngẫu nhiên
            System.out.print("Unscramble the word: ");
            for (char character : shuffledCharacters) {
                System.out.print(character);
            }
            System.out.println();

            // Nhập câu trả lời từ người chơi
            System.out.print("Your answer: ");
            String guess = scanner.nextLine();

            // Kiểm tra xem câu trả lời có đúng không
            if (guess.equalsIgnoreCase(word)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect! The correct word is: " + word);
            }
            System.out.println();
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
