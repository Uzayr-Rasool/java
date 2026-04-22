import java.util.Scanner;

public class StudentManagementSystem {

    static String[] studentIDs = new String[100];
    static String[] names = new String[100];
    static String[] departments = new String[100];
    static String[] semesters = new String[100];
    static String[] attendances = new String[100];
    static String[] cgpas = new String[100];
    static String[] passwords = new String[100];
    static int studentCount = 0;

    static String adminUser = "admin";
    static String adminPass = "1234";

    static Scanner input = new Scanner(System.in);

    // ===== HEADER =====
    public static void header(String title) {
        System.out.println("\n--- " + title + " ---\n");
    }

    // ===== VALID INT =====
    public static int getValidInt() {
        while (!input.hasNextInt()) {
            System.out.print("Invalid input! Enter number: ");
            input.next();
        }
        return input.nextInt();
    }

    // ===== ATTENDANCE =====
    public static int getValidAttendance() {
        int att;
        while (true) {
            System.out.print("Attendance (1-100): ");
            att = getValidInt();
            if (att >= 1 && att <= 100) return att;
            System.out.println("Must be 1-100\n");
        }
    }

    // ===== CGPA =====
    public static double getValidCgpa() {
        double cgpa;
        while (true) {
            System.out.print("CGPA (0.0-4.0): ");
            while (!input.hasNextDouble()) {
                System.out.print("Enter valid number: ");
                input.next();
            }
            cgpa = input.nextDouble();
            if (cgpa >= 0.0 && cgpa <= 4.0) return cgpa;
            System.out.println("Must be 0.0 - 4.0\n");
        }
    }

    // ===== ADMIN RECOVERY =====
    public static void adminRecovery() {
        header("Admin Recovery");

        System.out.println("1. Change Username");
        System.out.println("2. Change Password");

        int choice = getValidInt();

        if (choice == 1) {
            System.out.print("Current password: ");
            if (input.next().equals(adminPass)) {
                System.out.print("New username: ");
                adminUser = input.next();
                System.out.println("Username updated");
            } else {
                System.out.println("Wrong password");
            }
        }

        else if (choice == 2) {
            System.out.print("Current username: ");
            if (input.next().equals(adminUser)) {
                System.out.print("New password: ");
                adminPass = input.next();
                System.out.println("Password updated");
            } else {
                System.out.println("Wrong username");
            }
        }
    }

    // ===== STUDENT RECOVERY =====
    public static void studentRecovery() {
        header("Student Recovery");

        System.out.print("Enter ID: ");
        String id = input.next();

        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                System.out.print("New password: ");
                passwords[i] = input.next();
                System.out.println("Password updated");
                return;
            }
        }

        System.out.println("Invalid ID");
    }

    // ===== ADD =====
    public static void addStudent() {
        header("Add Student");

        System.out.print("ID: ");
        String id = input.next();

        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                System.out.println("ID already exists\n");
                return;
            }
        }

        studentIDs[studentCount] = id;

        System.out.print("Name: ");
        names[studentCount] = input.next();

        System.out.print("Dept: ");
        departments[studentCount] = input.next();

        System.out.print("Semester: ");
        semesters[studentCount] = input.next();

        attendances[studentCount] = String.valueOf(getValidAttendance());
        cgpas[studentCount] = String.valueOf(getValidCgpa());

        System.out.print("Password: ");
        passwords[studentCount] = input.next();

        studentCount++;

        System.out.println("Student added\n");
    }

    // ===== VIEW =====
    public static void viewStudents() {
        header("All Students");

        if (studentCount == 0) {
            System.out.println("No Record\n");
            return;
        }

        System.out.println("ID\tName\tDept\tSem\tAtt\tCGPA\n");

        for (int i = 0; i < studentCount; i++) {
            System.out.println(studentIDs[i] + "\t" + names[i] + "\t" +
                    departments[i] + "\t" + semesters[i] + "\t" +
                    attendances[i] + "\t" + cgpas[i]);
        }
    }

    // ===== SEARCH =====
    public static void searchStudent() {
        header("Search Student");

        System.out.print("Enter ID: ");
        String id = input.next();

        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                System.out.println("\nStudent Found\n");
                System.out.println("ID: " + studentIDs[i]);
                System.out.println("Name: " + names[i]);
                System.out.println("Dept: " + departments[i]);
                System.out.println("Semester: " + semesters[i]);
                System.out.println("Attendance: " + attendances[i]);
                System.out.println("CGPA: " + cgpas[i]);
                return;
            }
        }

        System.out.println("Student Not Found\n");
    }

    // ===== UPDATE =====
    public static void updateStudent() {
        header("Update Student");

        System.out.print("Enter ID: ");
        String id = input.next();

        int index = -1;

        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Not found\n");
            return;
        }


        System.out.print("Name: ");
        names[index] = input.next();

        System.out.print("Dept: ");
        departments[index] = input.next();

        System.out.print("Semester: ");
        semesters[index] = input.next();

        attendances[index] = String.valueOf(getValidAttendance());
        cgpas[index] = String.valueOf(getValidCgpa());

        System.out.println("Updated\n");
    }

    // ===== DELETE =====
    public static void deleteStudent() {
        header("Delete Student");

        System.out.print("Enter ID: ");
        String id = input.next();

        int index = -1;

        for (int i = 0; i < studentCount; i++) {
            if (studentIDs[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Not found\n");
            return;
        }

        System.out.print("Confirm (yes/no): ");
        if (!input.next().equalsIgnoreCase("yes")) {
            System.out.println("Cancelled\n");
            return;
        }

        for (int i = index; i < studentCount - 1; i++) {
            studentIDs[i] = studentIDs[i + 1];
            names[i] = names[i + 1];
            departments[i] = departments[i + 1];
            semesters[i] = semesters[i + 1];
            attendances[i] = attendances[i + 1];
            cgpas[i] = cgpas[i + 1];
            passwords[i] = passwords[i + 1];
        }

        studentCount--;

        System.out.println("Deleted\n");
    }

    // ===== PROFILE =====
    public static void showProfile(int i) {
        header("Profile");

        System.out.println("ID: " + studentIDs[i]);
        System.out.println("Name: " + names[i]);
        System.out.println("Dept: " + departments[i]);
        System.out.println("Semester: " + semesters[i]);
        System.out.println("Attendance: " + attendances[i]);
        System.out.println("CGPA: " + cgpas[i]);
    }

    // ===== MAIN =====
    public static void main(String[] args) {

        while (true) {

            header("Main Menu");

            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Quit");

            int a = getValidInt();

            // ===== ADMIN =====
            if (a == 1) {

                int attempts = 0;
                boolean login = false;

                while (attempts < 3) {

                    System.out.print("Username: ");
                    String u = input.next();

                    System.out.print("Password: ");
                    String p = input.next();

                    if (u.equals(adminUser) && p.equals(adminPass)) {
                        login = true;
                        break;
                    }

                    attempts++;
                    System.out.println("Invalid credentials");

                    if (attempts < 3) {
                        System.out.println("1. Retry");
                        System.out.println("2. Recovery");

                        if (getValidInt() == 2) adminRecovery();
                    }
                }

                if (login) {

                    while (true) {

                        header("Admin Menu");

                        System.out.println("1. Add Student");
                        System.out.println("2. View Students");
                        System.out.println("3. Search Student");
                        System.out.println("4. Update Student");
                        System.out.println("5. Delete Student");
                        System.out.println("6. Logout");

                        int c = getValidInt();

                        if (c == 1) addStudent();
                        else if (c == 2) viewStudents();
                        else if (c == 3) searchStudent();
                        else if (c == 4) updateStudent();
                        else if (c == 5) deleteStudent();
                        else if (c == 6) {
                            System.out.println("Logging out...");
                            break;
                        }
                        else {
                            System.out.println("Invalid choice");
                        }
                    }
                }
            }

            // ===== STUDENT =====
            else if (a == 2) {

                while (true) {

                    header("Student Menu");

                    System.out.println("1. Login");
                    System.out.println("2. Back");

                    int opt = getValidInt();

                    if (opt == 2) break;

                    int attempts = 0;
                    int index = -1;

                    while (attempts < 3) {

                        System.out.print("ID: ");
                        String id = input.next();

                        System.out.print("Password: ");
                        String pass = input.next();

                        for (int i = 0; i < studentCount; i++) {
                            if (studentIDs[i].equals(id) && passwords[i].equals(pass)) {
                                index = i;
                                break;
                            }
                        }

                        if (index != -1) break;

                        attempts++;
                        System.out.println("Invalid ID or Password");

                        if (attempts < 3) {
                            System.out.println("1. Retry");
                            System.out.println("2. Forgot Password");

                            if (getValidInt() == 2) studentRecovery();
                        }
                    }

                    if (index != -1) {

                        while (true) {

                            header("Dashboard");

                            System.out.println("1. View Profile");
                            System.out.println("2. Logout");

                            int b = getValidInt();

                            if (b == 1) showProfile(index);
                            else if (b == 2) break;
                        }
                    }
                }
            }

            // ===== QUIT =====
            else if (a == 3) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }
}
