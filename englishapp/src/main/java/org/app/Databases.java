package org.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Databases {
    static Connection connection;

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:databases.db");
            if (connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM av");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next() == true) {
                int id = resultSet.getInt(1);
                String word = resultSet.getString(2);
                String description = resultSet.getString(4);
                String pronounce = resultSet.getString(5);

                System.out.println(id + " | " + word + " | " + description + " | " + pronounce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
