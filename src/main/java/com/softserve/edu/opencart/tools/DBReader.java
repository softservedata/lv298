package com.softserve.edu.opencart.tools;

import com.softserve.edu.opencart.pages.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBReader implements IExternalReader {

    private final int firstColunm = 2;
    private String sqlSelect;

    public DBReader(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public String getSqlSelect() {
        return this.sqlSelect;
    }

    public String getPath() {
        return getSqlSelect();
    }

    public List<List<String>> getAllCells() {
        return getAllCells(sqlSelect);
    }

    public List<List<String>> getAllCells(String sqlSelect) {

        List<List<String>> allRecords = new ArrayList<List<String>>();
        List<String> rows = new ArrayList<String>();

        try (Statement st = Application.get().getConnection().createStatement
                ()) {
            try (ResultSet resultSet = st.executeQuery(sqlSelect)) {

                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = firstColunm; i <= columnCount; i++) {
                    rows.add(resultSet.getMetaData().getColumnName(i));
                }
                int recordCount = 1;
                while (resultSet.next()) {
                    recordCount++;
                    for (int i = firstColunm; i <= columnCount; i++) {
                        rows.add(resultSet.getString(i));
                    }
                }

                for (int i = 0; i < recordCount; i++) {
                    int start = i * (columnCount - 1);
                    int end = start + columnCount - 1;
                    allRecords.add(rows.subList(start, end));
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(String.format(DB_READING_ERROR, e));
        }

        return allRecords;
    }

}