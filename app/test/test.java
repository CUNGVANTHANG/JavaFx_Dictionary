package app.test;

import app.src.DictionaryCommandLine;
import app.src.DictionaryManagement;

public class test {
    public static void main(String[] args) {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        DictionaryCommandLine commandLine = new DictionaryCommandLine(dictionaryManagement);
        commandLine.dictionaryBasic();
    }
}
