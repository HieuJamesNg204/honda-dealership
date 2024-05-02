import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HondaService {
    private static Connection connection = DatabaseUtil.getConnection();

    public static int recordPurchase(int customerId,
                              int hondaId,
                              String purchaseLocation,
                              long onTheRoadPrice,
                              LocalDate purchaseDate) {
        String sql = "INSERT INTO honda_purchase (" +
                "customer_id, " +
                "honda_id, " +
                "purchase_location, " +
                "on_the_road_price, " +
                "purchase_date" +
                ") VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, hondaId);
            preparedStatement.setString(3, purchaseLocation);
            preparedStatement.setLong(4, onTheRoadPrice);
            preparedStatement.setDate(5, Date.valueOf(purchaseDate));

            int added = preparedStatement.executeUpdate();
            preparedStatement.close();
            return added;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static List<String> getAllPurchaseRecords() {
        List<String> records = new ArrayList<>();
        String sql = "SELECT " +
                "customer.id AS customerId," +
                "customer.first_name, " +
                "customer.last_name, " +
                "customer.email, " +
                "customer.phone, " +
                "customer.address, " +
                "honda.model, " +
                "honda.version, " +
                "honda_purchase.purchase_location, " +
                "honda_purchase.on_the_road_price, " +
                "honda_purchase.purchase_date " +
                "FROM honda_purchase " +
                "INNER JOIN honda ON honda_purchase.honda_id=honda.id " +
                "INNER JOIN customer ON honda_purchase.customer_id=customer.id;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String record = "Customer ID " + resultSet.getInt("customerId") + " - "
                        + resultSet.getString("first_name") + " "
                        + resultSet.getString("last_name") + " - " + resultSet.getString("email")
                        + " - " + resultSet.getString("phone") + " - "
                        + resultSet.getString("address") + ": Honda "
                        + resultSet.getString("model") + " " + resultSet.getString("version")
                        + " purchased in " + resultSet.getString("purchase_location") + " on "
                        + resultSet.getDate("purchase_date") + " - totally "
                        + resultSet.getLong("on_the_road_price") + " dongs on the road";

                records.add(record);
            }

            resultSet.close();
            preparedStatement.close();
            return records;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return new ArrayList<>();
        }
    }

    public static List<String> getRecordsByCustomerId(int customerId) {
        List<String> records = new ArrayList<>();
        String sql = "SELECT " +
                "customer.id AS customerId," +
                "customer.first_name, " +
                "customer.last_name, " +
                "customer.email, " +
                "customer.phone, " +
                "customer.address, " +
                "honda.model, " +
                "honda.version, " +
                "honda_purchase.purchase_location, " +
                "honda_purchase.on_the_road_price, " +
                "honda_purchase.purchase_date " +
                "FROM honda_purchase " +
                "INNER JOIN honda ON honda_purchase.honda_id=honda.id " +
                "INNER JOIN customer ON honda_purchase.customer_id=customer.id " +
                "WHERE customer.id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String record = "Customer ID " + resultSet.getInt("customerId") + " - "
                        + resultSet.getString("first_name") + " "
                        + resultSet.getString("last_name") + " - " + resultSet.getString("email")
                        + " - " + resultSet.getString("phone") + " - "
                        + resultSet.getString("address") + ": Honda "
                        + resultSet.getString("model") + " " + resultSet.getString("version")
                        + " purchased in " + resultSet.getString("purchase_location") + " on "
                        + resultSet.getDate("purchase_date") + " - totally "
                        + resultSet.getLong("on_the_road_price") + " dongs on the road";
                records.add(record);
            }

            resultSet.close();
            preparedStatement.close();
            return records;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return new ArrayList<>();
        }
    }
}