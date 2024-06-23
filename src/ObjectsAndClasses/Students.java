package ObjectsAndClasses;

import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Students {
    public Students(String firstName, String lastName, double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + String.format("%.2f", grade);
    }

    private String firstName;
    private String lastName;
    private double grade;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<Students> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            Students student = new Students(s[0], s[1], Double.parseDouble(s[2]));

            students.add(student);
        }
        students.sort(Comparator.comparing(Students::getGrade).reversed());
        for (Students student : students) {
            System.out.println(student.toString());
        }
    }

}
