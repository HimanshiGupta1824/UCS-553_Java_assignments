import java.io.*;
import java.util.ArrayList;
import java.util.List;


class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d Name: %-15s Dept: %-10s Salary: %.2f",
                id, name, department, salary);
    }
}

public class EmployeeSerialization {

    static void serialize(List<Employee> employees, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(employees);
            System.out.println(employees.size() + " employees serialized to " + fileName);
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static List<Employee> deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        String fileName = "employees.ser";

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Rahul Mehta", "IT", 65000));
        employees.add(new Employee(2, "Sneha Kapoor", "HR", 52000));
        employees.add(new Employee(3, "Arjun Nair", "Finance", 71000));
        employees.add(new Employee(4, "Neha Gupta", "Marketing", 58000));

        serialize(employees, fileName);

        System.out.println("\n--- Deserialized Employee Records ---");
        for (Employee e : deserialize(fileName)) {
            System.out.println(e);
        }
    }
}