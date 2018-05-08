package com.softserve.edu.opencart.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements IFileReader {

//    private static class ExcelFactory implements IRowFactory {
//        public List<String> updateRow(List<String> row) {
//            return row;
//        }
//    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

	private final String PATH_SEPARATOR = "/";
    private static final int MAIN_BY_NUMBER_SHEET = 0;
//    private IRowFactory rowFactory; 
    private String filename;
    private String path;

    public ExcelReader(String filename) {
	        this.filename = filename;
	        this.path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath().substring(1);
	        //this.rowFactory = new ExcelFactory();
	    }

//    public IRowFactory getRowFactory() {
//        return this.rowFactory;
//    }

    public String getFilename() {
        return this.filename;
    }

    public String getPath() {
        return this.path;
    }

    public List<List<String>> getAllCells() {
        return getAllCells(path);
    }

    public List<List<String>> getAllCells(String path) {
        List<List<String>> allCells = new ArrayList<List<String>>();
        DataFormatter formatter = new DataFormatter();
        // For *.xls files
        // HSSFWorkbook workBook;
        // For *.xlsx files
        // XSSFWorkbook workBook = null;
        XSSFWorkbook workBook = null;
        Sheet sheet = null;
        try( InputStream inputStream = new FileInputStream(path)) {
            // System.out.println("filename=" + connection);
            // workBook = new HSSFWorkbook(inputStream);
            workBook = new XSSFWorkbook(inputStream);
            // sheet = (new XSSFWorkbook(inputStream)).getSheetAt(0);
            sheet = workBook.getSheetAt(MAIN_BY_NUMBER_SHEET);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format(FILE_NOT_FOUND_EXCEPTION, path));
        } catch (IOException e) {
            throw new RuntimeException(String.format(FILE_NOT_READ_EXCEPTION, path));
        }
        Iterator<Row> rowsIterator = sheet.iterator();
        while (rowsIterator.hasNext()) {
            Row row = rowsIterator.next();
            Iterator<Cell> cellsIterator = row.iterator();
            List<String> allRowCells = new ArrayList<String>();
            // System.out.println("\n ***allRowCells = ");
            while (cellsIterator.hasNext()) {
                // For Old Version of XSSFWorkbook
                // String cell = cellsIterator.next().getStringCellValue();
                // For New Version of XSSFWorkbook
                String cell = formatter.formatCellValue(cellsIterator.next());
                // allCells.add(cellsIterator.next().getStringCellValue());
                //System.out.println("\t\t*** cell = " + cell);
                allRowCells.add(cell);
                // System.out.print(" " + cell);
            }
            allCells.add(allRowCells);
            // System.out.println();
        }
        if (workBook != null) {
            try {
                workBook.close();
            } catch (IOException e) {
                throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
            }
        }
        return allCells;
    }

}