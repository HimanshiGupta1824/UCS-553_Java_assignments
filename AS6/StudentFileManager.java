import java.io.*;


public class StudentFileManager {

    
    static void copyBinaryFile(String source, String destination) {
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            long total = 0;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
                total += bytesRead;
            }
            System.out.println("Binary file copied successfully (" + total + " bytes).");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while copying file: " + e.getMessage());
        }
    }


    static void writeStudents(String fileName, String[] students) {
        try (FileWriter fw = new FileWriter(fileName)) {
            for (String s : students) {
                fw.write(s + System.lineSeparator());
            }
            System.out.println("Student details written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

 
    static void readStudents(String fileName) {
        try (FileReader fr = new FileReader(fileName)) {
            System.out.println("\n--- Student Records ---");
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }

    static void createSampleBinaryFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            for (int i = 0; i < 256; i++) {
                fos.write(i);
            }
        } catch (IOException e) {
            System.out.println("Error creating sample file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        createSampleBinaryFile("source.dat");
        copyBinaryFile("source.dat", "copy.dat");

      
        String[] students = {
            "RollNo: 101, Name: Aman Sharma, Course: B.Tech CSE, Marks: 85",
            "RollNo: 102, Name: Priya Verma, Course: B.Tech ECE, Marks: 91",
            "RollNo: 103, Name: Rohit Singh, Course: B.Tech ME, Marks: 78"
        };
        writeStudents("students.txt", students);
        readStudents("students.txt");
    }
}