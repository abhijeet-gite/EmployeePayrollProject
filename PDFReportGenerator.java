//PDFReportGenerator.java

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.sql.*;

public class PDFReportGenerator {

    public static void generateMonthlyReport(String filePath) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Monthly Salary Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Table with 5 columns
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            // Table Headers
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Department");
            table.addCell("Base Salary");
            table.addCell("Bonus");

            // DB Connect
            Connection conn = DBUtils.getConnection();
            String query = "SELECT * FROM employees";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                table.addCell(String.valueOf(rs.getInt("id")));
                table.addCell(rs.getString("name"));
                table.addCell(rs.getString("department"));
                table.addCell(String.valueOf(rs.getDouble("base_salary")));
                table.addCell(String.valueOf(rs.getDouble("bonus")));
            }

            conn.close();
            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
