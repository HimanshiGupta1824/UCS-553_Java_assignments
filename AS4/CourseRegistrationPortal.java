import java.util.*;

public class CourseRegistrationPortal {

    static class Course {
        private final String courseCode;
        private final String courseName;
        private final int capacity;

        private final HashSet<String> enrolledStudentIds = new HashSet<>();
        private final ArrayList<String> prerequisiteCodes = new ArrayList<>();
        private final LinkedList<String> waitlist = new LinkedList<>();

        public Course(String courseCode, String courseName, int capacity) {
            if (capacity < 1) {
                throw new IllegalArgumentException("Capacity must be at least 1.");
            }
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.capacity = capacity;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCapacity() {
            return capacity;
        }

        public HashSet<String> getEnrolledStudentIds() {
            return enrolledStudentIds;
        }

        public ArrayList<String> getPrerequisiteCodes() {
            return prerequisiteCodes;
        }

        public LinkedList<String> getWaitlist() {
            return waitlist;
        }

        public void addPrerequisite(String prerequisiteCode) {
            if (prerequisiteCode != null && !prerequisiteCode.isBlank()
                    && !prerequisiteCodes.contains(prerequisiteCode)) {
                prerequisiteCodes.add(prerequisiteCode);
            }
        }

        public void display() {
            System.out.println("\nCourse: " + courseCode + " - " + courseName);
            System.out.println("Capacity: " + capacity);
            System.out.println("Enrolled (" + enrolledStudentIds.size() + "): "
                    + enrolledStudentIds);
            System.out.println("Prerequisites: " + prerequisiteCodes);
            System.out.println("Waitlist (first-served): " + waitlist);
        }
    }

    // Course-code lookup is O(1) on average.
    private final HashMap<String, Course> courses = new HashMap<>();

    // student ID -> courses the student has completed
    private final HashMap<String, HashSet<String>> completedCourses = new HashMap<>();

    public void addCourse(String code, String name, int capacity, String... prerequisites) {
        if (courses.containsKey(code)) {
            System.out.println("Course " + code + " already exists.");
            return;
        }

        Course course = new Course(code, name, capacity);
        for (String prerequisite : prerequisites) {
            course.addPrerequisite(prerequisite);
        }
        courses.put(code, course);
        System.out.println("Added course: " + code + " - " + name);
    }

    public void recordCompletedCourse(String studentId, String courseCode) {
        if (!courses.containsKey(courseCode)) {
            System.out.println("Cannot record completion: unknown course " + courseCode);
            return;
        }
        completedCourses.computeIfAbsent(studentId, id -> new HashSet<>()).add(courseCode);
        System.out.println(studentId + " completed " + courseCode);
    }

    private boolean hasMetPrerequisites(String studentId, Course course) {
        HashSet<String> completed =
                completedCourses.getOrDefault(studentId, new HashSet<>());

        for (String prerequisite : course.getPrerequisiteCodes()) {
            if (!completed.contains(prerequisite)) {
                return false;
            }
        }
        return true;
    }

    public void registerStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Registration failed: course " + courseCode + " does not exist.");
            return;
        }
        if (studentId == null || studentId.isBlank()) {
            System.out.println("Registration failed: student ID cannot be empty.");
            return;
        }
        if (course.getEnrolledStudentIds().contains(studentId)) {
            System.out.println(studentId + " is already enrolled in " + courseCode + ".");
            return;
        }
        if (course.getWaitlist().contains(studentId)) {
            System.out.println(studentId + " is already on the waitlist for " + courseCode + ".");
            return;
        }
        if (!hasMetPrerequisites(studentId, course)) {
            System.out.println("Registration denied for " + studentId + " in " + courseCode
                    + ": prerequisites not completed.");
            return;
        }

        if (course.getEnrolledStudentIds().size() < course.getCapacity()) {
            course.getEnrolledStudentIds().add(studentId);
            System.out.println(studentId + " successfully enrolled in " + courseCode + ".");
        } else {
            course.getWaitlist().addLast(studentId);
            System.out.println(courseCode + " is full. " + studentId
                    + " added to the end of the waitlist.");
        }
    }

   
    public void dropStudent(String studentId, String courseCode) {
        Course course = courses.get(courseCode);

        if (course == null) {
            System.out.println("Drop failed: course " + courseCode + " does not exist.");
            return;
        }
        if (!course.getEnrolledStudentIds().remove(studentId)) {
            System.out.println(studentId + " is not enrolled in " + courseCode + ".");
            return;
        }

        System.out.println(studentId + " dropped " + courseCode + ".");
        promoteFromWaitlist(course);
    }

    private void promoteFromWaitlist(Course course) {
        Iterator<String> iterator = course.getWaitlist().iterator();

        while (iterator.hasNext()) {
            String candidate = iterator.next();

           
            if (hasMetPrerequisites(candidate, course)) {
                iterator.remove();
                course.getEnrolledStudentIds().add(candidate);
                System.out.println("Waitlist promotion: " + candidate + " enrolled in "
                        + course.getCourseCode() + ".");
                return;
            } else {
               
                iterator.remove();
                System.out.println("Removed " + candidate + " from " + course.getCourseCode()
                        + " waitlist because prerequisites are no longer satisfied.");
            }
        }

        System.out.println("No eligible student is waiting for " + course.getCourseCode() + ".");
    }

    public void displayCourse(String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course " + courseCode + " not found.");
        } else {
            course.display();
        }
    }

    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course course : courses.values()) {
            course.display();
        }
    }

    public static void main(String[] args) {
        CourseRegistrationPortal portal = new CourseRegistrationPortal();

        System.out.println("===== COURSE REGISTRATION PORTAL =====");

        portal.addCourse("CS101", "Programming Fundamentals", 2);
        portal.addCourse("CS201", "Data Structures", 2, "CS101");
        portal.addCourse("CS301", "Algorithms", 1, "CS201");

        System.out.println("\n--- Record completed courses ---");
        portal.recordCompletedCourse("S101", "CS101");
        portal.recordCompletedCourse("S102", "CS101");
        portal.recordCompletedCourse("S103", "CS101");
        portal.recordCompletedCourse("S101", "CS201");
        portal.recordCompletedCourse("S102", "CS201");

        System.out.println("\n--- Test prerequisite checking ---");
        portal.registerStudent("S104", "CS201"); // Denied: CS101 not completed

        System.out.println("\n--- Register for CS201 (capacity = 2) ---");
        portal.registerStudent("S101", "CS201");
        portal.registerStudent("S102", "CS201");
        portal.registerStudent("S103", "CS201"); // Waitlisted

        System.out.println("\n--- Try duplicate registration ---");
        portal.registerStudent("S101", "CS201");

        System.out.println("\n--- Course status before drop ---");
        portal.displayCourse("CS201");

        System.out.println("\n--- Drop a student and promote waitlist ---");
        portal.dropStudent("S101", "CS201");

        System.out.println("\n--- Course status after promotion ---");
        portal.displayCourse("CS201");

        System.out.println("\n--- All courses ---");
        portal.displayAllCourses();
    }
}
