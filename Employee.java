public class Employee {
    private int id;
    private String name;
    private String department;
    private double baseSalary;
    private double bonus;

    // ✅ Main Constructor with bonus
    public Employee(int id, String name, String department, double baseSalary, double bonus) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
    }

    // ✅ Additional Constructor for compatibility with PayrollApp.java
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = salary;
        this.bonus = 0.0;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getBaseSalary() { return baseSalary; }
    public double getBonus() { return bonus; }

    public double getSalary() { // ✅ For PayrollApp.java compatibility
        return baseSalary + bonus;
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setBaseSalary(double salary) { this.baseSalary = salary; }
    public void setSalary(double salary) { this.baseSalary = salary; } // ✅ Optional duplicate setter
    public void setBonus(double bonus) { this.bonus = bonus; }

    // Salary Calculation
    public double calculateTax() {
        return 0.1 * (baseSalary + bonus); // 10% tax
    }

    public double calculateNetPay() {
        return baseSalary + bonus - calculateTax();
    }

    // Display Method
    public void displayInfo() {
        System.out.println("ID: " + id +
                " | Name: " + name +
                " | Dept: " + department +
                " | Base Salary: ₹" + baseSalary +
                " | Bonus: ₹" + bonus +
                " | Net Pay: ₹" + calculateNetPay());
    }
}


