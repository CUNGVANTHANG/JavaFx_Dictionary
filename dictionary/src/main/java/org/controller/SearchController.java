package org.controller;

import org.base.Bookmark;
import org.base.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController extends GeneralController {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DictionaryManagement.insertFromFile();
        handleStyle();
        handleEvent();
    }
}