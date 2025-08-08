import java.io.FileWriter;
import java.io.IOException;

public class SalaryCalculator implements Payroll {
    @Override
    public void calculateSalarySlip(Employee e) {
        double base = e.getBaseSalary();
        double bonus = e.getBonus();
        double tax = 0.1 * base;
        double deductions = 0.05 * base;
        double netPay = base + bonus - tax - deductions;

        // 📄 Salary Slip (Console Output)
        System.out.println("\n===== SALARY SLIP =====");
        System.out.println("Name        : " + e.getName());
        System.out.println("Department  : " + e.getDepartment());
        System.out.println("Base Salary : ₹" + base);
        System.out.println("Bonus       : ₹" + bonus);
        System.out.println("Tax (10%)   : ₹" + tax);
        System.out.println("Deductions  : ₹" + deductions);
        System.out.println("Net Pay     : ₹" + netPay);
        System.out.println("========================");

        // 💾 Salary Slip Export to .txt
        try {
            FileWriter fw = new FileWriter("SalarySlip_" + e.getId() + ".txt");
            fw.write("===== SALARY SLIP =====\n");
            fw.write("Name        : " + e.getName() + "\n");
            fw.write("Department  : " + e.getDepartment() + "\n");
            fw.write("Base Salary : ₹" + base + "\n");
            fw.write("Bonus       : ₹" + bonus + "\n");
            fw.write("Tax (10%)   : ₹" + tax + "\n");
            fw.write("Deductions  : ₹" + deductions + "\n");
            fw.write("Net Pay     : ₹" + netPay + "\n");
            fw.write("========================\n");
            fw.close();

            System.out.println("✅ Salary Slip exported as SalarySlip_" + e.getId() + ".txt");
        } catch (IOException ex) {
            System.out.println("❌ Error saving salary slip: " + ex.getMessage());
        }
    }
}

