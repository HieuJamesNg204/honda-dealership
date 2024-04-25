import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HondaDAO {
    private Connection connection;

    public HondaDAO() {
        this.connection = DatabaseUtil.getConnection();
        if (this.connection == null) {
            throw new RuntimeException("Connection failed to initialise.");
        }
    }

    public void addHonda(Honda honda) {
        String sql = "INSERT INTO honda (model, colour, listed_price) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, honda.getModel());
            preparedStatement.setString(2, honda.getColour());
            preparedStatement.setDouble(3, honda.getListedPrice());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public List<Honda> getAllHondas() {
        List<Honda> hondas = new ArrayList<>();
        String sql = "SELECT * FROM honda;";
        return null;
    }
}