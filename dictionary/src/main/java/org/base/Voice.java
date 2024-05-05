package org.base;

import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;


public class Voice {
    private static final String API_KEY = "ee1a861047db41e3aed6cca75554a826";
    private static final String filePath = "C:\\Users\\PC\\Desktop\\HK2\\GROUP11-UETOASIS\\dictionary\\src\\main\\resources\\voice\\audio.mp3";

    public static String voiceNameUS;
    public static String voiceNameUK;
    public static String language = "en-gb";
    public static String name = "Alice";
    public static double speed = 1;

    public static void speakWord(String word) throws Exception {
        VoiceProvider tts = new VoiceProvider(API_KEY);
        VoiceParameters params = new VoiceParameters(word, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);
        params.setVoice(name);
        params.setRate((int) Math.round(-2.9936 * speed * speed + 15.2942 * speed - 12.7612));

        byte[] voice = tts.speech(params);

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(voice))) {
            javax.sound.sampled.AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try (SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info)) {
                line.open(format);
                line.start();

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                    line.write(buffer, 0, bytesRead);
                }

                line.drain();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        speakWord("how are you, and you");
    }
}
