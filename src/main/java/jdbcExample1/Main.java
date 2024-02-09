package jdbcExample1;

import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /*
        Alternatively, placing code in try(code here) will automatically close the resources used
        so .close() methods not necessary, but may require updating project settings.
        Project Structure >  Project > Project language level > Choose "8 - Lambdas, type annotations etc."
        Resources will be closed once try-catch block is exited.
         */

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/user/database.db");
            Statement stmt = conn.createStatement(); // Create a statement

            // Another way of executing and assigning result set to a variable
            //stmt.execute("select * from albums");
            //ResultSet rs = stmt.getResultSet();
            ResultSet rs = stmt.executeQuery("SELECT * FROM column1"); // Execute the query,
            // returns a boolean that will be true if the statement executed returned an instance of the result set class
            // returns false if it returned an update count or no results
            // an update query would return false, becuase it returns a number of records that were updated
            String filenName = "output.txt";
            PrintWriter writer = new PrintWriter(filenName); // Open a PrintWriter to write to a text file


            while (rs.next()) { // Iterate over the result set and write the data to the text file
                writer.print("column1: " + rs.getString("column1")+ " "); // Print value for column1
                writer.println("column2: " + rs.getString("column2")); // Print value for column2

            }

            rs.close(); // Close the resources
            stmt.close(); // Close the resources
            conn.close(); // Close the resources
            writer.close(); // Close the resources

            System.out.println("Data has been written to " + filenName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
