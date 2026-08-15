package library.model;

public class DigitalResource extends LibraryResource implements Printable {

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        return overdueDays * 2;
    }

    @Override
    public void printDetails() {
        System.out.println("----- DIGITAL RESOURCE DETAILS -----");
        displayBasicDetails();
        System.out.println("Resource Type: Digital Resource");
    }
}