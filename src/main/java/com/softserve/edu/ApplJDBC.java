package com.softserve.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApplJDBC {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Start...");
        Connection con = null;
        String username = "lv298";
        String password = "Lv298_Set";
        //String username = "root";
        //String password = "Lv298_Set";
        //
        // Sybase
        //String URL = "jdbc:jtds:sqlserver://ssu-sql12/ssu-oms;instance=tc;";
        //DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        //
        // MySQL
        //String URL = "jdbc:mysql://192.168.103.210:3306/lv298";
        String URL = "jdbc:mysql://192.168.103.210:3306/opencart";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        //
        // Load the driver
        con = DriverManager.getConnection(URL, username, password);
        if (con != null) {
            System.out.println("Connection Successful! \n");
        } else {
            System.out.println("Connection ERROR \n");
            System.exit(1);
        }
        Statement st = con.createStatement();
        // Statement allows you to send inquiries database
        //st.execute("INSERT INTO Users(IsUserActive,Balance,Email,FirstName,LastName,Login,Password,CustomerTypeRef,RegionRef,RoleRef) VALUES (1,null,'mail@gmail.com','melanka','horoshko','melanka','qwerty',null,1,1);");
        //
        ////st.execute("UPDATE customer_login SET total='1' WHERE email LIKE 'jar%';");
        //ResultSet rs = st.executeQuery("select * from temp");
        ResultSet rs = st.executeQuery("select * from customer_login;");
        //ResultSet rs = st.executeQuery("select * from Roles");
        //
        int columnCount = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(rs.getMetaData().getColumnName(i) + "\t");
        }
        System.out.println("");
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
        System.out.println();
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (con != null) {
            con.close();
        }
        System.out.println("DONE");
    }
}
