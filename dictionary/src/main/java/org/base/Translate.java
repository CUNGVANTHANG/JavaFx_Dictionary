package org.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate {
    public static String googleTranslate(String langFrom, String langTo, String text) throws IOException {
        String apiKey = "AKfycbzxtNpZD2Ogs4oeUnj8nTaCmPlKwgwsLWPasyIsLQPB_WXvKdKU";
        String urlScript = "https://script.google.com/macros/s/" + apiKey + "/exec?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlScript);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public static void main(String[] args) {
        Thread translateThread = new Thread(() -> {
            try {
                String result = googleTranslate("en", "vi", "Hello"); // Gọi phương thức translate với các tham số tương ứng
                System.out.println("Translation result: " + result); // In kết quả dịch
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        translateThread.start();
    }
}