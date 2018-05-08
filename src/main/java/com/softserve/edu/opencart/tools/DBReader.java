package com.softserve.edu.opencart.tools;

import com.softserve.edu.opencart.pages.Application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBReader implements ISqlReader {

    private final int firstColunm = 1;
    private String sqlSelect;

    public DBReader(String sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public String getSqlSelect() {
        return this.sqlSelect;
    }

    public String getQuery() {
        return getSqlSelect();
    }

    public List<List<String>> getAllCells() {
        return getAllCells(sqlSelect);
    }

    public List<List<String>> getAllCells(String sqlSelect) {

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> rows = new ArrayList<>();

        try (Statement st = Application.get().getConnection().createStatement
                ()) {
            try (ResultSet resultSet = st.executeQuery(sqlSelect)) {

                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = firstColunm; i <= columnCount; i++) {
                    rows.add(resultSet.getMetaData().getColumnLabel(i));
                }
                int recordCount = 1;
                while (resultSet.next()) {
                    recordCount++;
                    for (int i = firstColunm; i <= columnCount; i++) {
                        rows.add(resultSet.getString(i));
                    }
                }

                for (int i = 0; i < recordCount; i++) {
                    int start = i * (columnCount);
                    int end = start + columnCount;
                    result.add(rows.subList(start, end));
                }
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(String.format(DB_READING_ERROR, e));
        }

        return result;
    }

}