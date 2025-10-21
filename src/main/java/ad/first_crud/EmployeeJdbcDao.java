package ad.first_crud;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJdbcDao {
    public List<Employee> getAllEmployees() {
        List<Employee> result = new ArrayList<>();
        String SQL_SELECT = "Select * from EMPLOYEE";
       return connection(SQL_SELECT);

    }

    private static List<Employee> connection(String SQL_SELECT) {
       ArrayList<Employee> result = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root", "root")) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal salary = resultSet.getBigDecimal("SALARY");
                Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");

                Employee obj = new Employee();
                obj.setId(id);
                obj.setName(name);
                obj.setSalary(salary);
                // Timestamp -> LocalDateTime
                obj.setCreatedDate(createdDate.toLocalDateTime());

                result.add(obj);
            }
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Employee> getEmployeesWithLessSalary(double lesssalary) {
        String SQL_SELECT = "Select * from EMPLOYEE where salary <=" + lesssalary;
        return connection(SQL_SELECT);
    }
}
