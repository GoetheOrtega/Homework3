import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "22121";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Informatica";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        executeQuery(connection);
        insertData(connection);

        connection.close();
    }

    private static void executeQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from accounts");

        while (result.next()) {
            System.out.println(result.getInt("id") + result.getString("first_name"));
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        int age = scanner.nextInt();

        String sqlInsertUser = "INSERT INTO accounts (first_name, second_name, age) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1, firstName);
        preparedStatement.setString(2, lastName);
        preparedStatement.setInt(3, age);

        int affectedRows = preparedStatement.executeUpdate();

        System.out.println(affectedRows + " rows affected");
    }
}

