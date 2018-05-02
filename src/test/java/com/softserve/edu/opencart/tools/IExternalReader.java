package com.softserve.edu.opencart.tools;

import java.util.List;

public interface IExternalReader {
    String DB_READING_ERROR = "Data Base reading error, %s";

    List<List<String>> getAllCells();
    List<List<String>> getAllCells(String path);
}
