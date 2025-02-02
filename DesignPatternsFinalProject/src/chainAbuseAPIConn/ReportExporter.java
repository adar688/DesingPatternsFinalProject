package chainAbuseAPIConn;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportExporter {
    
    public static void exportToCSV(List<Report> reports, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Address,Number of Abuses,Link\n");
            for(Report report : reports) {
                writer.write(String.format("%s,%d,%s\n",
                    escapeCSV(report.getAddress()),
                    report.getAbuseCount(),
                    escapeCSV(report.getLink())
                ));
            }
        }
    }
    
    /**
     * Escapes special characters in CSV data to ensure correct formatting.
     * Handles null values, quotation marks, commas, and newlines in the data.
     * 
     * @param data The string to be escaped for CSV format
     * @return The escaped string
     */
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