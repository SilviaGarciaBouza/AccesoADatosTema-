package ad.first_crud;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {

    public static void main(String[] args) {

        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional since java 1.6
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // auto close connection
        EmployeeJdbcDao ejdao= new EmployeeJdbcDao();
        ejdao.getAllEmployees().forEach(e-> System.out.println(e));
        ejdao.getEmployeesWithLessSalary(55000d).forEach(e-> System.out.println(e));
    }
}