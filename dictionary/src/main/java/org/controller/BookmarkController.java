package org.controller;

import org.base.Bookmark;

import java.net.URL;
import java.util.ResourceBundle;

public class BookmarkController extends GeneralController{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bookmark.insertFromFile();
        handleEvent();
    }
}
