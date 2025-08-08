import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class PDFUtils {

    public static void generateSalarySlip(Employee e) {
        Document document = new Document();
        try {
            String fileName = "SalarySlip_" + e.getId() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
            Paragraph title = new Paragraph("Salary Slip", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Empty line

            // Employee Info
            document.add(new Paragraph("Employee ID: " + e.getId()));
            document.add(new Paragraph("Name: " + e.getName()));
            document.add(new Paragraph("Department: " + e.getDepartment()));
            document.add(new Paragraph("Basic Salary: ₹" + e.getSalary()));
            document.add(new Paragraph("Bonus: ₹" + e.getBonus()));
            document.add(new Paragraph("Total Salary: ₹" + (e.getSalary() + e.getBonus())));

            document.close();
            System.out.println("📄 Salary Slip generated: " + fileName);
        } catch (Exception ex) {
            System.out.println("❌ Error generating PDF: " + ex.getMessage());
        }
    }
}

