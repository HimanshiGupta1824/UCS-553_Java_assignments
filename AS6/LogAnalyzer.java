import java.io.*;

public class LogAnalyzer {

    static void createSampleLog(String fileName) {
        String[] lines = {
            "2026-11-06 09:00:01 INFO  Application started",
            "2026-11-06 09:00:05 INFO  User admin logged in",
            "2026-11-06 09:01:12 WARNING Disk usage at 85%",
            "2026-11-06 09:02:30 ERROR Database connection failed",
            "2026-11-06 09:02:35 INFO  Retrying database connection",
            "2026-11-06 09:02:40 INFO  Database connected",
            "2026-11-06 09:05:00 WARNING Response time exceeded 2s",
            "2026-11-06 09:06:45 ERROR NullPointerException in OrderService",
            "2026-11-06 09:10:00 INFO  User admin logged out"
        };
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error creating sample log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputFile = "application.log";
        String outputFile = "filtered_log.txt";

        createSampleLog(inputFile);

        int totalEntries = 0, errorCount = 0, warningCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;   
                totalEntries++;

                if (line.contains("ERROR")) {
                    errorCount++;
                    bw.write(line);
                    bw.newLine();
                } else if (line.contains("WARNING")) {
                    warningCount++;
                    bw.write(line);
                    bw.newLine();
                }
            }

            bw.newLine();
            bw.write("----- Summary -----");
            bw.newLine();
            bw.write("Total log entries : " + totalEntries);
            bw.newLine();
            bw.write("ERROR entries     : " + errorCount);
            bw.newLine();
            bw.write("WARNING entries   : " + warningCount);
            bw.newLine();

        } catch (FileNotFoundException e) {
            System.out.println("Log file not found: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("Error processing log file: " + e.getMessage());
            return;
        }

        System.out.println("Total log entries : " + totalEntries);
        System.out.println("ERROR entries     : " + errorCount);
        System.out.println("WARNING entries   : " + warningCount);
        System.out.println("Filtered entries saved to " + outputFile);
    }
}