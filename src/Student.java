import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Student {
    private String firstName;
    private String lastName;
    private int age;
    private String hometown;

    public Student(String firstName, String lastName, int age, String hometown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hometown = hometown;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public static boolean isStudentExisting(List<Student> students, String firstName, String lastName) {
        for (Student student : students) {
            if (student.getFirstName().equals(firstName) &&
                student.getLastName().equals(lastName)) {
                return  true;
            }
        }
        return false;
    }

    private static Student getStudent(List<Student> students, String firstName, String lastName) {
        Student existingStudent = null;

        for (Student student : students) {
            if (student.getFirstName().equals(firstName) &&
                    student.getLastName().equals(lastName)) {
                existingStudent = student;
            }
        }
        return existingStudent;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        String data = sc.nextLine();
        while (!data.equals("end")) {
            String[] tokens = data.split(" ");

            if(isStudentExisting(students, tokens[0], tokens[1])) {
                Student student = getStudent(students, tokens[0], tokens[1]);
                student.setFirstName(tokens[0]);
                student.setLastName(tokens[1]);
                student.setAge(Integer.parseInt(tokens[2]));
                student.setHometown(tokens[3]);
            }
            else {
                Student student = new Student(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3]);
                students.add(student);
            }

            data = sc.nextLine();
        }

        String city = sc.nextLine();
        List<Student> filterStudents = students.stream().filter(e -> e.getHometown().equals(city))
                .collect(Collectors.toList());
        for (Student student : filterStudents) {
            System.out.printf("%s %s is %d years old\n", student.getFirstName(), student.getLastName(), student.getAge());
        }
    }
}
