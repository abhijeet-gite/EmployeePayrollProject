import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PayrollGUI extends JFrame {

    JTextField idField, nameField, deptField, salaryField, bonusField;
    JButton saveBtn, viewBtn, updateBtn, deleteBtn, pdfBtn, reportBtn;

    public PayrollGUI() {
        setTitle("🧾 Employee Payroll GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));

        // Input fields
        add(new JLabel("ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Department:"));
        deptField = new JTextField();
        add(deptField);

        add(new JLabel("Base Salary:"));
        salaryField = new JTextField();
        add(salaryField);

        add(new JLabel("Bonus:"));
        bonusField = new JTextField();
        add(bonusField);

        // Buttons
        saveBtn = new JButton("💾 Save");
        updateBtn = new JButton("✏️ Update");
        deleteBtn = new JButton("🗑️ Delete");
        viewBtn = new JButton("📋 View All");
        pdfBtn = new JButton("🧾 Download PDF");
        reportBtn = new JButton("📈 Monthly Report");

        add(saveBtn);
        add(updateBtn);
        add(deleteBtn);
        add(viewBtn);
        add(pdfBtn);
        add(reportBtn);

        // Empty space
        add(new JLabel(""));

        // Button Listeners
        saveBtn.addActionListener(e -> saveEmployee());
        updateBtn.addActionListener(e -> updateEmployee());
        deleteBtn.addActionListener(e -> deleteEmployee());
        viewBtn.addActionListener(e -> showEmployeesTable());
        pdfBtn.addActionListener(e -> downloadSalaryPDF());
        reportBtn.addActionListener(e -> generateMonthlyReport());

        setVisible(true);
    }

    void saveEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String dept = deptField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());

            Connection conn = DBUtils.getConnection();
            String sql = "INSERT INTO employees (id, name, department, base_salary, bonus) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, dept);
            ps.setDouble(4, salary);
            ps.setDouble(5, bonus);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "✅ Employee saved!");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    void updateEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String dept = deptField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());

            Connection conn = DBUtils.getConnection();
            String sql = "UPDATE employees SET name=?, department=?, base_salary=?, bonus=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);
            ps.setDouble(4, bonus);
            ps.setInt(5, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "✅ Employee updated!");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    void deleteEmployee() {
        try {
            int id = Integer.parseInt(idField.getText());

            Connection conn = DBUtils.getConnection();
            String sql = "DELETE FROM employees WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int result = ps.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "🗑️ Employee deleted!");
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error: " + ex.getMessage());
        }
    }

    void showEmployeesTable() {
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT * FROM employees";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String[] columns = {"ID", "Name", "Department", "Base Salary", "Bonus", "Net Salary"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");
                double salary = rs.getDouble("base_salary");
                double bonus = rs.getDouble("bonus");
                double net = salary + bonus - 0.1 * (salary + bonus);

                Object[] row = {id, name, dept, salary, bonus, net};
                model.addRow(row);
            }

            JTable table = new JTable(model);
            JScrollPane scroll = new JScrollPane(table);
            scroll.setPreferredSize(new Dimension(600, 200));

            JOptionPane.showMessageDialog(this, scroll, "📋 All Employees", JOptionPane.INFORMATION_MESSAGE);

            stmt.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error showing data: " + ex.getMessage());
        }
    }

    void downloadSalaryPDF() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String dept = deptField.getText();
            double salary = Double.parseDouble(salaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());

            Employee emp = new Employee(id, name, dept, salary, bonus);
            PDFGenerator.generateSalarySlip(emp);
            JOptionPane.showMessageDialog(this, "✅ Salary Slip PDF generated!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ PDF Error: " + ex.getMessage());
        }
    }

    void generateMonthlyReport() {
        try {
            PDFReportGenerator.generateMonthlyReport("MonthlySalaryReport.pdf");
            JOptionPane.showMessageDialog(this, "📈 Monthly report generated!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Report Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new PayrollGUI();
    }
}




