package library.main;

import library.model.Book;
import library.model.DigitalResource;
import library.model.LibraryResource;
import library.model.Printable;
import library.service.LibraryService;
import library.util.InputValidator;

public class Main {

    public static void main(String[] args) {

        LibraryResource[] resources = new LibraryResource[5];

        int[] overdueDays = new int[5];

        resources[0] = new Book(
                101,
                "Java Programming",
                "James Gosling"
        );

        resources[1] = new DigitalResource(
                102,
                "Artificial Intelligence",
                "Stuart Russell"
        );

        resources[2] = new Book(
                103,
                "Data Structures",
                "Mark Allen"
        );

        resources[3] = new DigitalResource(
                104,
                "Machine Learning",
                "Tom Mitchell"
        );

        resources[4] = new Book(
                105,
                "Database Systems",
                "Raghu Ramakrishnan"
        );

        overdueDays[0] = 5;
        overdueDays[1] = 10;
        overdueDays[2] = 3;
        overdueDays[3] = 7;
        overdueDays[4] = 0;

        System.out.println("===== SMART LIBRARY RESOURCE MANAGEMENT SYSTEM =====");

        for (int i = 0; i < resources.length; i++) {

            if (!InputValidator.validateResourceId(
                    resources[i].getResourceId())) {

                System.out.println("Invalid Resource ID");
                continue;
            }

            if (!InputValidator.validateFineDays(
                    overdueDays[i])) {

                System.out.println("Invalid overdue days");
                continue;
            }

            Printable printable = (Printable) resources[i];

            printable.printDetails();

            System.out.println("Overdue Days: " + overdueDays[i]);

            double fine =
                    resources[i].calculateFine(overdueDays[i]);

            System.out.println("Fine: Rs. " + fine);

            System.out.println();
        }

        LibraryService service = new LibraryService();

        double totalFine =
                service.calculateTotalFine(resources, overdueDays);

        System.out.println("======================================");
        System.out.println("Total Fine: Rs. " + totalFine);

        LibraryResource.displayTotalResources();
    }
}