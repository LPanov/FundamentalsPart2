import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person {
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  name + " - " + age;
    }

    private String name;
    private int age;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Person> olders = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = scanner.nextLine();
            String[] token = s.split(" ");
            Person person = new Person(token[0], Integer.parseInt(token[1]));

            if (Integer.parseInt(token[1]) > 30) olders.add(person);
        }
        for (Person older : olders) {
            System.out.println(older.toString());
        }
    }
}