import java.util.*;

/*
 * Student Management System using Java Collections
 * Features:
 * - Add student
 * - Display students
 * - Search by ID
 * - Update marks
 * - Remove by ID
 * - Sort by marks
 */

class Student {

    // Encapsulation: private variables
    private int id;
    private String name;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // equals() and hashCode() based on student ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student s = (Student) obj;
        return this.id == s.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Display student details
    @Override
    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Course: " + course +
               ", Marks: " + marks;
    }
}

public class StudentManagementSystem {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student by ID");
            System.out.println("6. Sort Students by Marks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        displayStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateMarks();
                        break;
                    case 5:
                        removeStudent();
                        break;
                    case 6:
                        sortStudents();
                        break;
                    case 7:
                        System.out.println("Program exited successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter correct values.");
                sc.next(); // clear wrong input
            }

        } while (choice != 7);
    }

    // 1. Add Student
    private static void addStudent() {
        try {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();

            sc.nextLine(); // clear buffer
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            Student s = new Student(id, name, course, marks);

            if (students.contains(s)) {
                System.out.println("Student with this ID already exists!");
            } else {
                students.add(s);
                System.out.println("Student added successfully.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.next();
        }
    }

    // 2. Display All Students
    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // 3. Search Student by ID
    private static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student Found:");
                System.out.println(s);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // 4. Update Student Marks
    private static void updateMarks() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.print("Enter new marks: ");
                double marks = sc.nextDouble();
                s.setMarks(marks);
                System.out.println("Marks updated successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // 5. Remove Student by ID
    private static void removeStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                it.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // 6. Sort Students by Marks
    private static void sortStudents() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        System.out.println("Students sorted by marks (High to Low).");
    }
}
