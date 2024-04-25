import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class HondaPurchase {
    private Connection connection;

    public HondaPurchase() {
        this.connection = DatabaseUtil.getConnection();
        if (this.connection == null) {
            throw new RuntimeException("Connection failed to initialise.");
        }
    }

    public int recordPurchase(int customerId,
                              int hondaId,
                              String purchaseLocation,
                              double onTheRoadPrice,
                              LocalDate date) {
        String sql = "INSERT INTO honda_purchase (customer_id, " +
                "honda_id, purchase_location, on_the_road_price, purchase_date) VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, hondaId);
            preparedStatement.setString(3, purchaseLocation);
            preparedStatement.setDouble(4, onTheRoadPrice);
            preparedStatement.setDate(5, Date.valueOf(date));

            int added = preparedStatement.executeUpdate();
            preparedStatement.close();
            return added;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }
}