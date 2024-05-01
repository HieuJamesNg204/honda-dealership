import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HondaDAO {
    private static Connection connection;

    public HondaDAO() {
        connection = DatabaseUtil.getConnection();
        if (connection == null) {
            throw new RuntimeException("Connection failed to initialise.");
        }
    }

    public static int addHonda(Honda honda) {
        String sql = "INSERT INTO honda (model, version, listed_price) VALUES (?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, honda.getModel());
            preparedStatement.setString(2, honda.getVersion());
            preparedStatement.setDouble(3, honda.getListedPrice());

            int added = preparedStatement.executeUpdate();
            preparedStatement.close();
            return added;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static List<Honda> getAllHondas() {
        List<Honda> hondas = new ArrayList<>();
        String sql = "SELECT * FROM honda;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Honda honda = new Honda();

                honda.setId(resultSet.getInt("id"));
                honda.setModel(resultSet.getString("model"));
                honda.setVersion(resultSet.getString("version"));
                honda.setListedPrice(resultSet.getDouble("listed_price"));

                hondas.add(honda);
            }

            resultSet.close();
            preparedStatement.close();
            return hondas;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static List<Honda> getHondasByModel(String model) {
        List<Honda> hondas = new ArrayList<>();
        String sql = "SELECT * FROM honda WHERE model=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, model);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Honda honda = new Honda();

                honda.setId(resultSet.getInt("id"));
                honda.setModel(model);
                honda.setVersion(resultSet.getString("version"));
                honda.setListedPrice(resultSet.getDouble("listed_price"));

                hondas.add(honda);
            }

            resultSet.close();
            preparedStatement.close();
            return hondas;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static List<Honda> getHondasByPriceRange(double min, double max) {
        List<Honda> hondas = new ArrayList<>();
        String sql = "SELECT * FROM honda WHERE listed_price BETWEEN ? AND ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, min);
            preparedStatement.setDouble(2, max);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Honda honda = new Honda();

                honda.setId(resultSet.getInt("id"));
                honda.setModel(resultSet.getString("model"));
                honda.setVersion(resultSet.getString("version"));
                honda.setListedPrice(resultSet.getDouble("listed_price"));

                hondas.add(honda);
            }

            resultSet.close();
            preparedStatement.close();
            return hondas;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static Honda getHondaById(int id) {
        Honda honda = new Honda();
        String sql = "SELECT * FROM honda WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                honda.setId(id);
                honda.setModel(resultSet.getString("model"));
                honda.setVersion(resultSet.getString("version"));
                honda.setListedPrice(resultSet.getDouble("listed_price"));
            }

            resultSet.close();
            preparedStatement.close();
            return honda;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static List<String> getModelList() {
        List<String> models = new ArrayList<>();
        String sql = "SELECT DISTINCT model FROM honda;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                models.add("Honda " + resultSet.getString("model"));
            }

            resultSet.close();
            preparedStatement.close();
            return models;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static int updateHonda(String model, String version, double listedPrice) {
        String sql = "UPDATE honda SET listed_price=? WHERE model=? AND version=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, listedPrice);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, version);

            int updated = preparedStatement.executeUpdate();
            preparedStatement.close();
            return updated;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static int deleteHonda(String model, String version) {
        String sql = "DELETE FROM honda WHERE model=? AND version=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, model);
            preparedStatement.setString(2, version);

            int updated = preparedStatement.executeUpdate();
            preparedStatement.close();
            return updated;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }
}