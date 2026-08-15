package library.service;

import library.model.LibraryResource;

public class LibraryService {

    public double calculateTotalFine(
            LibraryResource[] resources,
            int[] overdueDays) {

        double totalFine = 0;

        for (int i = 0; i < resources.length; i++) {
            totalFine += resources[i].calculateFine(overdueDays[i]);
        }

        return totalFine;
    }
}