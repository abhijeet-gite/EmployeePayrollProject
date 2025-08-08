//PDFGenerator.java

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class PDFGenerator {

    public static void generateSalarySlip(Employee emp) {
        try {
            Document document = new Document();
            String fileName = "SalarySlip_" + emp.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph title = new Paragraph("🧾 Employee Salary Slip", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Employee Info
            document.add(new Paragraph("Employee ID: " + emp.getId()));
            document.add(new Paragraph("Name: " + emp.getName()));
            document.add(new Paragraph("Department: " + emp.getDepartment()));
            document.add(new Paragraph("Base Salary: ₹" + emp.getBaseSalary()));
            document.add(new Paragraph("Bonus: ₹" + emp.getBonus()));
            document.add(new Paragraph("Tax (10%): ₹" + emp.calculateTax()));
            document.add(new Paragraph("Net Pay: ₹" + emp.calculateNetPay()));
            document.add(new Paragraph("\nThank you for your contribution!\n"));

            document.close();
            System.out.println("✅ PDF generated: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 

