package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    // Method to establish the database connection
    public Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:Bookstore.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to select all books from the database and properly close the connection
    public void selectAllBooks() {
        String sql = "SELECT * FROM books";
        try (Connection connection = this.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement.");
            e.printStackTrace();
        }
        // The try-with-resources statement ensures that the Connection, Statement, and ResultSet are all closed properly.
    }

    // Main method that serves as the entry point of the program
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();

        // Optionally, call the method to select all books
        dbConnector.selectAllBooks();
    }
}
