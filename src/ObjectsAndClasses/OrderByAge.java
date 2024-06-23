package ObjectsAndClasses;

import java.util.*;

public class OrderByAge {
    private  String name;
    private String ID;
    private int age;

    public OrderByAge(String name, String ID, int age) {
        this.name = name;
        this.ID = ID;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " with ID: " + ID + " is "+age+" years old." ;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        List<OrderByAge> orders = new ArrayList<>();

        while (!s.equals("End")) {
            String[] token = s.split(" ");

            OrderByAge client = new OrderByAge(token[0], token[1], Integer.parseInt(token[2]));
            orders.add(client);

            s = sc.nextLine();
        }
        //Collections.sort(orders, Comparator.comparing(ObjectsAndClasses.OrderByAge::getAge));
        orders.sort(Comparator.comparing(OrderByAge::getAge));
        for (OrderByAge order : orders) {
            System.out.println(order.toString());
        }

    }
}
