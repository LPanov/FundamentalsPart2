package MapsLambdaStreamAPI;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {
    private String name;
    private double damage;
    private double health;
    private double armor;

    public DragonArmy(double damage, double health, double armor) {
        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }

    public double getArmor() {
        return armor;
    }

    @Override
    public String toString() {
            return "damage: " + String.format("%.0f", damage) +
                    ", health: " + String.format("%.0f", health) +
                    ", armor: " + String.format("%.0f", armor);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, Map<String, DragonArmy>> dragons = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().split(" ");

            String type = t[0];
            String name = t[1];
            double damage = (t[2].equals("null")) ? 45 : Double.parseDouble(t[2]);
            double health = (t[3].equals("null")) ? 250 : Double.parseDouble(t[3]);
            double armor = (t[4].equals("null")) ? 10 : Double.parseDouble(t[4]);

            DragonArmy dragon = new DragonArmy(damage, health, armor);

            dragons.putIfAbsent(type, new TreeMap<>());
            dragons.get(type).putIfAbsent(name, dragon);

            if (dragons.get(type).containsKey(name)) {
                dragons.get(type).put(name, dragon);
            }
        }

        dragons.entrySet().stream()
                .forEach(entry ->{

                    String type = entry.getKey();
                    double damage = entry.getValue().values().stream().mapToDouble(DragonArmy::getDamage).sum()/entry.getValue().size();
                    double health = entry.getValue().values().stream().mapToDouble(DragonArmy::getHealth).sum()/entry.getValue().size();
                    double armor = entry.getValue().values().stream().mapToDouble(DragonArmy::getArmor).sum()/entry.getValue().size();

                    System.out.printf("%s::(%.2f/%.2f/%.2f)%n", type, damage, health, armor);
                    entry.getValue().entrySet().stream()
                            .forEach(subEntry -> System.out.printf("-%s -> %s%n", subEntry.getKey(), subEntry.getValue()));
                });
    }
}
