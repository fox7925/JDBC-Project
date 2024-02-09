package jdbcExample2;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            //connection.setAutoCommit(false); //jdbc commits changes automatically, if you don't commit then data lost when connection is closed
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists contacts " +
                    "(name varchar(12), phone INTEGER(10), email varchar(255))"); // Fixed missing parentheses

            // Fixed missing parentheses and commas in the insert statements
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Abe', 3053053000, 'abe@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Bert', 3053053001, 'bert@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Carl', 3053053002, 'carl@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Douglas', 3053053003, 'douglas@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Earl', 3053053004, 'earl@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Frank', 3053053005, 'frank@email.com')");
            statement.execute("insert into contacts (name, phone, email) " +
                    "values('Gabriel', 3053053006, 'gabriel@email.com')");

            statement.execute("update contacts set phone = 444 where name = 'Abe'");
            statement.execute("delete from contacts where name = 'Abe'");
            statement.execute("select * from contacts");
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                if (name.length() < 12) {
                    // Pad the name with spaces to make it 12 characters long
                    name = String.format("%-12s", name);
                }
                System.out.println(name + "\t" +
                        resultSet.getString("phone") + "\t" +
                        resultSet.getString("email"));
            }


            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }
}
