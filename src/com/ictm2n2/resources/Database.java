package com.ictm2n2.resources;

import java.sql.*;

public class Database {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test", "root", "");

            Statement stmt = conn.createStatement();

            String strSelect = "SELECT id, name FROM Users";
            System.out.println("The SQL statement is: " + strSelect + "\n");

            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The records selected are:");
            int rowCount = 0;

            while (rset.next()) {
                int id = rset.getInt("id");
                String name = rset.getString("name");
                System.out.println(id + ", " + name);
                ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
