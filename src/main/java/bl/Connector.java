package bl;

import java.sql.*;

public class Connector {
    private final String URL = "jdbc:mysql://localhost:3306/hibernate?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false ";
    private final String USERNAME = "root";
    private final String PASSWORD = "18111992";

    public Connection getConneсtion() throws SQLException {
        try {
            System.out.println("Устанавливаем соединение с базой");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return  connection;
        } catch (Exception e) {
            System.out.println("Соединение не удалось получить");
            return null;
        }
    }
}