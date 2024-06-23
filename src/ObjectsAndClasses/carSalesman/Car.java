package ObjectsAndClasses.carSalesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Car {
    private String model;
    private Engine engine;
    private int weight;
    private String color;

    static class Engine {
        private String model;
        private int power;
        private int displacement;
        private String efficiency;

        public Engine(String model, int power, int displacement, String efficiency) {
            this.model = model;
            this.power = power;
            this.displacement = displacement;
            this.efficiency = efficiency;
        }
        public Engine(String model, int power, int displacement) {
            this.model = model;
            this.power = power;
            this.displacement = displacement;
            this.efficiency = "n/a";
        }
        public Engine(String model, int power, String efficiency) {
            this.model = model;
            this.power = power;
            this.displacement = 0;
            this.efficiency = efficiency;
        }
        public Engine(String model, int power) {
            this.model = model;
            this.power = power;
            this.displacement = 0;
            this.efficiency = "n/a";
        }

        public String getModel() {
            return model;
        }

        public int getPower() {return power; }

        public int getDisplacement() {return displacement; }

        public String getEfficiency() { return efficiency;}

        @Override
        public String toString() {
            if (displacement == 0) {
                return model + ":\n" +
                        "\tPower: " + power + '\n' +
                        "\tDisplacement: " + "n/a" + '\n' +
                        "\tEfficiency: " + efficiency + '\n';
            }
            else {
                return model + ":\n" +
                        "  Power: " + power + '\n' +
                        "  Displacement: " + displacement + '\n' +
                        "  Efficiency: " + efficiency;
            }

        }
    }

    public Car(String model, Engine engine, int weight, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = color;
    }
    public Car(String model, Engine engine, int weight) {
        this.model = model;
        this.engine = engine;
        this.weight = weight;
        this.color = "n/a";
    }
    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.weight = 0;
        this.color = "n/a";
    }
    public Car(String model, Engine engine, String color) {
        this.model = model;
        this.engine = engine;
        this.weight = 0;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public static boolean existEngine(String model, List<Engine> engines) {
        for (Engine engine : engines) {
            if (engine.getModel().equals(model)) return true;
        }
        return  false;
    }
    public static Engine getEngine (String model, List<Engine> engines) {
        for (Engine engine : engines) {
            if (engine.getModel().equals(model)) return engine;
        }
        return null;

    }

    @Override
    public String toString() {
        if (weight > 0) {
            if (engine.getDisplacement() > 0) {
                return model + ":\n" +
                        "  " + engine.getModel() + ":\n" +
                        "    " + "Power: " + engine.getPower() + "\n" +
                        "    " + "Displacement: " + engine.getDisplacement() + "\n" +
                        "    " + "Efficiency: " + engine.getEfficiency() + "\n" +
                        "  Weight: " + weight + "\n" +
                        "  Color: " + color;
            } else {
                return model + ":\n" +
                        "  " + engine.getModel() + ":\n" +
                        "    " + "Power: " + engine.getPower() + "\n" +
                        "    " + "Displacement: " + "n/a" + "\n" +
                        "    " + "Efficiency: " + engine.getEfficiency() + "\n" +
                        "  Weight: " + weight + "\n" +
                        "  Color: " + color;
            }

        } else {
            if (engine.getDisplacement() > 0) {
                return model + ":\n" +
                        "  " + engine.getModel() + ":\n" +
                        "    " + "Power: " + engine.getPower() + "\n" +
                        "    " + "Displacement: " + engine.getDisplacement() + "\n" +
                        "    " + "Efficiency: " + engine.getEfficiency() + "\n" +
                        "  Weight: " + "n/a" + "\n" +
                        "  Color: " + color;
            } else {
                return model + ":\n" +
                        "  " + engine.getModel() + ":\n" +
                        "    " + "Power: " + engine.getPower() + "\n" +
                        "    " + "Displacement: " + "n/a" + "\n" +
                        "    " + "Efficiency: " + engine.getEfficiency() + "\n" +
                        "  Weight: " + "n/a" + "\n" +
                        "  Color: " + color;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nE = Integer.parseInt(sc.nextLine());
        List<Car> cars = new ArrayList<>();
        List<Engine> engines = new ArrayList<>();

        for (int i = 0; i < nE; i++) {
            String[] s = sc.nextLine().split(" ");
            if (s.length == 4) {
                Engine engine = new Engine(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]), s[3]);
                engines.add(engine);
            }
            else if (s.length == 2) {
                Engine engine = new Engine(s[0], Integer.parseInt(s[1]));
                engines.add(engine);
            }
            else if (s.length == 3) {
                if (s[2].matches("\\d+")) {
                    Engine engine = new Engine(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
                    engines.add(engine);
                }
                else {
                    Engine engine = new Engine(s[0], Integer.parseInt(s[1]), s[2]);
                    engines.add(engine);
                }
            }
        }
//        for (Engine engine:engines) {
//            System.out.println(engine);
//        }

        int nC = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < nC; i++) {
            String[] s = sc.nextLine().split(" ");
            Engine eng = Car.getEngine(s[1], engines);
            if (s.length == 4) {
                Car car = new Car(s[0], eng, Integer.parseInt(s[2]), s[3]);
                cars.add(car);
            }
            else if (s.length == 2) {
                Car car = new Car(s[0], eng);
                cars.add(car);
            }
            else if (s.length == 3) {
                if (s[2].matches("\\d+")) {
                    Car car = new Car(s[0], eng, Integer.parseInt(s[2]));
                    cars.add(car);
                } else {
                    Car car = new Car(s[0], eng, s[2]);
                    cars.add(car);
                }
            }
        }

        for (Car car : cars) {
            System.out.println(car.toString());
        }
    }
}
