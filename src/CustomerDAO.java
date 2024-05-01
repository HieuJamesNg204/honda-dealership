import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private static Connection connection = DatabaseUtil.getConnection();

    public static int addCustomer(Customer customer) {
        String sql = "INSERT INTO customer (first_name, last_name, email, phone, address) " +
                "VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setString(5, customer.getAddress());

            int added = preparedStatement.executeUpdate();
            preparedStatement.close();
            return added;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = new Customer();

                customer.setId(resultSet.getInt("id"));
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddress(resultSet.getString("address"));

                customers.add(customer);
            }

            resultSet.close();
            preparedStatement.close();
            return customers;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return new ArrayList<>();
        }
    }

    public static Customer getCustomerById(int id) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM customer WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                customer.setId(id);
                customer.setFirstName(resultSet.getString("first_name"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPhone(resultSet.getString("phone"));
                customer.setAddress(resultSet.getString("address"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return customer;
    }

    public static int updateCustomer(int id, String email, String phone, String address) {
        String sql = "UPDATE customer SET email=?, phone=?, address=? WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, address);
            preparedStatement.setInt(4, id);

            int updated = preparedStatement.executeUpdate();
            preparedStatement.close();
            return updated;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static int deleteCustomer(int id) {
        String sql = "DELETE FROM customer WHERE id=?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int updated = preparedStatement.executeUpdate();
            preparedStatement.close();
            return updated;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return 0;
        }
    }
}