import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewEmployees {
    public static void main(String[] args) {
        try (Connection conn = DBUtils.getConnection()) {
            String sql = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("------ All Employees ------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                double baseSalary = rs.getDouble("base_salary");
                double bonus = rs.getDouble("bonus");

                System.out.println("ID: " + id + ", Name: " + name + ", Department: " + department +
                                   ", Base Salary: " + baseSalary + ", Bonus: " + bonus);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error while fetching employees: " + e.getMessage());
        }
    }
}

