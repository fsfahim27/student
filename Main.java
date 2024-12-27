//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.ArrayList;
import java.util.List;

// Abstract class User
abstract class User {
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

// Admin class inheriting from User
class Admin extends User {
    public Admin(String id, String name, String email) {
        super(id, name, email);
    }

    public void generateReport() {
        System.out.println("Report generated by Admin: " + getName());
    }
}

// Instructor class inheriting from User
class Instructor extends User {
    private List<Course> assignedCourses = new ArrayList<>();

    public Instructor(String id, String name, String email) {
        super(id, name, email);
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
        System.out.println("Course assigned to Instructor: " + getName());
    }

    public void uploadGrade(Student student, Course course, int grade) {
        course.setGrade(student, grade);
        System.out.println("Grade uploaded by Instructor: " + getName());
    }
}

// Student class inheriting from User
class Student extends User {
    private List<Course> enrolledCourses = new ArrayList<>();

    public Student(String id, String name, String email) {
        super(id, name, email);
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.addStudent(this);
        System.out.println("Student enrolled: " + getName());
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
}

// Course class
class Course {
    private String courseId;
    private String courseName;
    private Instructor instructor;
    private List<Student> enrolledStudents = new ArrayList<>();
    private List<Integer> grades = new ArrayList<>();

    public Course(String courseId, String courseName, Instructor instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
        System.out.println("Student added to course: " + student.getName());
    }

    public void setGrade(Student student, int grade) {
        grades.add(grade);
        System.out.println("Grade set for student: " + student.getName());
    }
}

// Main class to test the implementation
class UniversityCourseManagementSystem {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("I001", "Jane Smith", "jane@example.com");
        Course course = new Course("C001", "Introduction to Java", instructor);
        Student student = new Student("S001", "John Doe", "john@example.com");

        student.enrollInCourse(course);
        instructor.uploadGrade(student, course, 95);

        Admin admin = new Admin("A001", "Admin User", "admin@example.com");
        admin.generateReport();
    }
}