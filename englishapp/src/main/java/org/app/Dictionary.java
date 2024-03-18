package org.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
    public Dictionary() {

    }

    static String getPath() {
        // Get relative path
        String filePath = "/src/main/resources/test.txt";

        // Get absolute path
        String currentDirectory = System.getProperty("user.dir");
        currentDirectory = currentDirectory.replace("\\", "/");

        return currentDirectory + filePath;
    }

    public static void main(String[] args) {
        try {
            // Khởi tạo FileReader để đọc file
            FileReader fileReader = new FileReader(getPath());

            // Khởi tạo BufferedReader để đọc từng dòng trong file
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Biến để lưu từng dòng trong file
            String line;

            // Đọc từng dòng trong file cho đến khi đọc hết
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("@")) {
                    // Chia dòng thành từ và phát âm
                    String[] parts = line.split("/", 2); // Chia dòng thành 2 phần, cắt ở dấu '/'

                    // Lấy từ sau ký tự '@' và loại bỏ các khoảng trắng xung quanh
                    String word = parts[0].substring(1).trim();

                    // Lấy phát âm sau ký tự '/'
                    String pronunciation = parts[1].trim();

                    // Hiển thị từ và phát âm
                    System.out.println("Word: " + word);
                    System.out.println("Pronunciation: " + pronunciation);
                } else if (line.startsWith("*")) {
                    String type = line.substring(1).trim();
                    System.out.println("Type: " + type);
                } else if (line.startsWith("-")) {
                    String meaning = line.substring(1).trim();

                    System.out.println("Meaning: " + meaning);
                } else if (line.startsWith("=")) {
                    String[] parts = line.split("\\+", 2);

                    String example = parts[0].substring(1).trim();
                    String explain = parts[1].substring(1).trim();

                    System.out.println("Example: " + example);
                    System.out.println("Explain: " + explain);
                }
            }

            // Đóng FileReader và BufferedReader sau khi đọc xong
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
