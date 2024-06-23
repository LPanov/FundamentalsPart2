package ObjectsAndClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Car {
    static class  Engine {
        private int speed;
        private int power;

        public Engine(int speed, int power) {
            this.speed = speed;
            this.power = power;
        }

        public int getPower() {
            return power;
        }
    }
    public static class Cargo {
        private int weight;
        private String type;
        public Cargo(int weight, String type) {
            this.weight = weight;
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }
    static class Tire {
        private double pressure;
        private int age;

        public Tire(double pressure, int age) {
            this.pressure = pressure;
            this.age = age;
        }

        public double getPressure() {
            return pressure;
        }
    }
    private String model;
    private Engine engine;
    private Cargo cargo;
    private List<Tire> tires;

    public Car(String model, Engine engine, Cargo cargo) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tires = new ArrayList<>();
    }

    public void addTire(Tire tire){
        this.tires.add(tire);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public List<Tire> getTires() {
        return tires;
    }

    public String getModel() {
        return model;
    }
    public boolean lowPressure(List<Tire> tires) {
        for (Tire tire : tires) {
            if (tire.getPressure() < 1) return true;
        }
        return false;
    }
    public Engine getEngine() {
        return engine;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            Car.Engine engine = new Car.Engine(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            Cargo cargo = new Cargo(Integer.parseInt(s[3]), s[4]);
            Car car = new Car(s[0], engine, cargo);
            for (int j = 5; j < 13; j+=2) {
                Tire tire = new Tire(Double.parseDouble(s[j]), Integer.parseInt(s[j+1]));
                car.addTire(tire);
            }
            cars.add(car);
        }
        String line = sc.nextLine();
        if (line.equals("fragile")) {
            for (Car car : cars) {
               if (car.getCargo().getType().equals("fragile") &&
                       car.lowPressure(car.getTires())) {
                   System.out.println(car.getModel());
               }
            }
        }
        else if (line.equals("flamable")) {
            for (Car car : cars) {
                if (car.getCargo().getType().equals("flamable") &&
                        car.getEngine().getPower() > 250) {
                    System.out.println(car.getModel());
                }
            }
        }
    }
}
