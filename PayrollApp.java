import java.util.*;

public class PayrollApp {
    static Scanner sc = new Scanner(System.in);
    static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n📋 Payroll Menu");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Generate Salary Slip");
            System.out.println("4. Exit");
            System.out.println("5. Search Employee by ID");
            System.out.println("6. Update Employee");
            System.out.println("7. Delete Employee");
            System.out.println("8. Calculate Total Payroll ₹");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> generateSalarySlip();
                case 4 -> System.out.println("👋 Exiting Payroll App. Goodbye!");
                case 5 -> searchEmployee();
                case 6 -> updateEmployee();
                case 7 -> deleteEmployee();
                case 8 -> calculateTotalPayroll();
                default -> System.out.println("⚠️ Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    static void addEmployee() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // flush
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Base Salary: ");
        double salary = sc.nextDouble();
        System.out.print("Enter Bonus: ");
        double bonus = sc.nextDouble();

        Employee e = new Employee(id, name, dept, salary, bonus);
        employees.add(e);
        System.out.println("✅ Employee added successfully.");
    }

    static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("📭 No employees to display.");
            return;
        }
        for (Employee e : employees) {
            e.displayInfo();
        }
    }

    static void generateSalarySlip() {
        System.out.print("Enter Employee ID for salary slip: ");
        int id = sc.nextInt();
        for (Employee e : employees) {
            if (e.getId() == id) {
                PDFUtils.generateSalarySlip(e); // ✅ PDF Generation Call
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("🔍 Employee Found:");
                e.displayInfo();
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        for (Employee e : employees) {
            if (e.getId() == id) {
                sc.nextLine(); // flush
                System.out.print("Enter New Name (" + e.getName() + "): ");
                e.setName(sc.nextLine());
                System.out.print("Enter New Department (" + e.getDepartment() + "): ");
                e.setDepartment(sc.nextLine());
                System.out.print("Enter New Base Salary (" + e.getBaseSalary() + "): ");
                e.setBaseSalary(sc.nextDouble());
                System.out.print("Enter New Bonus (" + e.getBonus() + "): ");
                e.setBonus(sc.nextDouble());
                System.out.println("✅ Employee Updated.");
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        Iterator<Employee> itr = employees.iterator();
        while (itr.hasNext()) {
            Employee e = itr.next();
            if (e.getId() == id) {
                itr.remove();
                System.out.println("🗑 Employee Deleted.");
                return;
            }
        }
        System.out.println("❌ Employee not found.");
    }

    static void calculateTotalPayroll() {
        double total = 0;
        for (Employee e : employees) {
            total += e.getBaseSalary() + e.getBonus();
        }
        System.out.println("💰 Total Payroll (All Employees): ₹" + total);
    }
}



