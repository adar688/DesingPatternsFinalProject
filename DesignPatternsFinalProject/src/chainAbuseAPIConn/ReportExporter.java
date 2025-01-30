package chainAbuseAPIConn;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExporter {
    
    public static void exportToCSV(List<Report> reports, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.write("Address,Number of Abuses,Link\n");
            
            // Write data
            for(Report report : reports) {
                writer.write(String.format("%s,%d,%s\n",
                    escapeCSV(report.getAddress()),
                    report.getAbuseCount(),
                    escapeCSV(report.getLink())
                ));
            }
        }
    }
    
    // Helper method to handle commas in data
    private static String escapeCSV(String data) {
        if (data == null) {
            return "";
        }
        String escapedData = data.replaceAll("\"", "\"\"");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("\n")) {
            return "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}