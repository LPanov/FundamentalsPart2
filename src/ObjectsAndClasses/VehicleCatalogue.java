package ObjectsAndClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleCatalogue {
    private String type;
    private String model;
    private String color;
    private int horsePower;

    public VehicleCatalogue(String type, String model, String color, int horsePower) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
    private String toUpperFirst(String s) {
        s = s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
        return s;
    }
    @Override
    public String toString() {
        return "Type: " + toUpperFirst(type) + '\n' +
                "Model: " + model + '\n' +
                "Color: " + color + '\n' +
                "Horsepower: " + horsePower;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int contCars = 0;
        int countTrucks = 0;
        int sumCarsHP = 0;
        int sumTrucksHP = 0;
        double carsHP = 0;
        double trucksHP = 0;
        List<VehicleCatalogue> cars = new ArrayList<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("End")) break;
            String[] token = s.split(" ");

            VehicleCatalogue auto;
            auto = new VehicleCatalogue(token[0], token[1], token[2], Integer.parseInt(token[3]));
            cars.add(auto);

            if (token[0].equals("car")) {
                sumCarsHP += Integer.parseInt(token[3]);
                contCars++;
            }
            else if (token[0].equals("truck")) {
                sumTrucksHP += Integer.parseInt(token[3]);
                countTrucks++;
            }
        }
        while (true) {
            String s = sc.nextLine();
            if (s.equals("Close the Catalogue")) break;
            for (int i = 0; i < cars.size(); i++) {
                if (s.equals(cars.get(i).getModel())) {
                    System.out.println(cars.get(i).toString());
                }
            }
        }
        carsHP = (contCars > 0) ? (double) sumCarsHP / contCars : 0;
        trucksHP =(countTrucks > 0) ? (double) sumTrucksHP / countTrucks : 0;

        System.out.println("Cars have average horsepower of: "+String.format("%.2f", carsHP)+".");
        System.out.println("Trucks have average horsepower of: "+String.format("%.2f", trucksHP)+".");

    }
}
