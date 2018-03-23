package com.softserve.edu.opencart.tools;

import com.softserve.edu.opencart.data.users.IUser;

import java.sql.*;

public class DataBaseConnection {
    private final String host = "jdbc:mysql:databases.000webhost.com/localhost/id5044102_nazarondb";
    private final String user = "id5044102_nazaron1995";
    private final String password = "qwerty12345";

    private Connection connection;
    private Statement statement;

    DataBaseConnection() {
        try {
            this.connection = DriverManager.getConnection(host, user, password);
        } catch (SQLException e) {
            System.out.println("Can't connect to DB");
            e.printStackTrace();
        }
    }

    private Statement createState(){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Can't create DB statement");
            e.printStackTrace();
        }
        return statement;
    }

    public String getUserByEmail(IUser user){
        String query = "Select email from oc_costumer where email='" + user.getEmail() + "';";
        ResultSet res = null;
        String output = "";
        try {
            res = createState().executeQuery(query);
            while(res.next()){
                output += res.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
