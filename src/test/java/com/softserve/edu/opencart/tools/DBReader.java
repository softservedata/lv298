package com.softserve.edu.opencart.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.opencart.pages.Application;

public final class DBReader implements IExternalReader {
    
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
		List<List<String>> allCells = new ArrayList<List<String>>();
		List<String> row;
		//
		Statement statement;
		ResultSet resultSet;
		try {
			statement = Application.get().getConnection().createStatement();
			resultSet = statement.executeQuery(sqlSelect);
			while (resultSet.next()) {
				row = new ArrayList<>();
	            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	            	row.add(resultSet.getString(i));
	            }
	            allCells.add(row);
	        }
	        if (resultSet != null) {
	        	resultSet.close();
	        }
	        if (statement != null) {
	        	statement.close();
	        }
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException(String.format(DB_READING_ERROR, e));
		}
		//
		return allCells;
	}

}
