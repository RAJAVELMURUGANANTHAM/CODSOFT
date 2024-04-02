import java.io.*;
import java.util.*;

public class Student_Management_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        try {
            while (true) {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Search Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        addStudent(scanner, sms);
                        break;
                    case 2:
                        removeStudent(scanner, sms);
                        break;
                    case 3:
                        searchStudent(scanner, sms);
                        break;
                    case 4:
                        sms.displayAllStudents();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void addStudent(Scanner scanner, StudentManagementSystem sms) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        System.out.print("Enter blood group: ");
        String bloodGroup = scanner.nextLine();

        if (!name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty() && !bloodGroup.isEmpty()) {
            sms.addStudent(new Student(name, rollNumber, grade, bloodGroup));
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Invalid input. Please enter all fields.");
        }
    }

    private static void removeStudent(Scanner scanner, StudentManagementSystem sms) {
        if (sms.isEmpty()) {
            System.out.println("No students available to remove.");
            return;
        }

        System.out.print("Enter roll number of student to remove: ");
        String rollToRemove = scanner.nextLine();
        Student studentToRemove = sms.searchStudent(rollToRemove);
        if (studentToRemove != null) {
            sms.removeStudent(studentToRemove);
            System.out.println("Student removed successfully.");
            sms.displayAllStudents();
        } else {
            System.out.println("No student details found with the provided roll number.");
        }
    }

    private static void searchStudent(Scanner scanner, StudentManagementSystem sms) {
        if (sms.isEmpty()) {
            System.out.println("No students available to search.");
            return;
        }

        System.out.print("Enter roll number of student to search: ");
        String rollToSearch = scanner.nextLine();
        Student foundStudent = sms.searchStudent(rollToSearch);
        if (foundStudent != null) {
            System.out.println("Student found: " + foundStudent);
        } else {
            System.out.println("Student not found.");
        }
    }

}

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String rollNumber;
    private String grade;
    private String bloodGroup;

    public Student(String name, String rollNumber, String grade, String bloodGroup) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + ", Blood Group: " + bloodGroup;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("All Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }
}
