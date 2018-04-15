package com.softserve.edu.opencart.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbReader {

//    private static class ExcelFactory implements IRowFactory {
//        public List<String> updateRow(List<String> row) {
//            return row;
//        }
//    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

//    private IRowFactory rowFactory;
    private String URL;
    private String username;
    private String password;

    public DbReader(String URL,String username,String password) {
	        this.URL = URL;
            this.username = username;
            this.password = password;

	    }

   /* public List<List<String>> getAllRecords() throws SQLException {
        return getAllRecords(URL);
    }
*/
    public List<List<String>> getAllRecords()throws SQLException {
        List<List<String>> allRecords = new ArrayList<List<String>>();
        List<String> columns = new ArrayList<String>();
        List<String> records = new ArrayList<String>();
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        try (Connection con = DriverManager.getConnection(getURL(), getUsername(), getPassword())) {
            if (con != null) {
                System.out.println("Connection Successful! \n");
            } else {
                System.out.println("Connection ERROR \n");
                System.exit(1);
            }
            try (Statement st = con.createStatement()) {
                // Statement allows you to send inquiries database
                //st.execute("INSERT INTO Users(IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) VALUES (1,null,'mail@gmail.com','melanka','horoshko','melanka','qwerty',null,1,1);");
                try (ResultSet rs = st.executeQuery("select * from products")) {
                    //ResultSet rs = st.executeQuery("select * from Roles");
                    //
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int i = 2; i <= columnCount; i++) {
                        columns.add(rs.getMetaData().getColumnName(i));
                    }
                    allRecords.add(columns);
                    int recordCount = 0;
                    while (rs.next()) {
                        recordCount++;
                        for (int i = 2; i <= columnCount; i++) {
                            records.add(rs.getString(i));
                        }
                    }

                    for (int i = 0; i < recordCount; i++) {
                        int start = i*(columnCount-1);
                        int end = start + columnCount -1;
                        allRecords.add(records.subList(start,end));
                    }
                }
            }
        }
      return allRecords;
    }

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}