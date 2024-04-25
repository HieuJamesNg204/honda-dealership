import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String URL = "jdbc:postgresql://localhost:5432/honda_dealer_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ViDNm04-003065";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
}