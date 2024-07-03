package MapsLambdaStreamAPI;

import java.util.*;

public class Exercises {
    Scanner sc = new Scanner(System.in);
    public void countChars() {
        String word = sc.nextLine();
        LinkedHashMap<Character, Integer> counts = new LinkedHashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (ch != ' ') {
                if (counts.containsKey(ch)) {
                    counts.put(ch, counts.get(ch) + 1);
                }
                else {
                    counts.put(ch, 1);
                }
            }
        }

        for (var entry : counts.entrySet()) {
            System.out.printf("%c -> %d%n", entry.getKey(), entry.getValue());
        }
    }

    public void minerTask() {

        LinkedHashMap<String, Integer> quantities = new LinkedHashMap<>();

        while (true) {
            String resource = sc.nextLine();
            if (resource.equals("stop")) break;
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantities.containsKey(resource)) {
                quantities.put(resource, quantities.get(resource) + quantity);
            } else {
                quantities.put(resource, quantity);
            }
        }

        for (var entry : quantities.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }

    public void orders() {

        LinkedHashMap<String, Double> products = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> quantities = new LinkedHashMap<>();

        while (true) {
            String[] product = sc.nextLine().split(" ");
            if(product[0].equals("buy")) break;

            String name = product[0];
            int quantity = Integer.parseInt(product[2]);

            if (quantities.containsKey(name)) {

                quantities.put(name, quantities.get(name) + quantity);
            } else {
                quantities.put(name, quantity);
            }
            double price = Double.parseDouble(product[1])*quantities.get(name);

            products.put(name, price);
        }

        for (var entry : products.entrySet()) {
            System.out.printf("%s -> %.2f%n", entry.getKey(), entry.getValue());
        }
    }
    public void softUniParking() {
        int n = Integer.parseInt(sc.nextLine());
        LinkedHashMap<String, String> plates = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            if(s[0].equals("register")) {
                if (plates.containsKey(s[1])) {
                    System.out.println("ERROR: already registered with plate number "+plates.get(s[1]));
                } else {
                    plates.put(s[1], s[2]);
                    System.out.println(s[1] + " registered "+s[2]+" successfully");
                }
            }
            else if (s[0].equals("unregister")) {
                if (plates.containsKey(s[1])) {
                    plates.remove(s[1]);
                    System.out.println(s[1] + " unregistered successfully");
                } else {
                    System.out.println("ERROR: user "+s[1]+" not found");
                }
            }
        }

        for (var entry : plates.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    public void courses() {
        //List<Courses> names = new ArrayList<>();
        LinkedHashMap<String, List<String>> names = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("end")) break;

            String[] token = s.split(" : ");

            if (!names.containsKey(token[0])) {
                names.put(token[0], new ArrayList<>());
                names.get(token[0]).add(token[1]);
            } else {
                if (!names.get(token[0]).contains(token[1])) {
                    names.get(token[0]).add(token[1]);
                }
            }
        }

        /*for (var entry : names.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
            for (String name : entry.getValue()) {
                System.out.println("-- " + name);
            }
        }*/
        names.entrySet().stream().sorted((f,s) ->
                s.getValue().size()-f.getValue().size())
                .forEach(entry -> {
                    System.out.printf("%s: %d%n", entry.getKey(), entry.getValue().size());
                    entry.getValue().stream().sorted(String::compareTo).
                    forEach(e -> System.out.printf("-- %s%n", e));
        });
    }

    public void studentAcademy() {
        int n = Integer.parseInt(sc.nextLine());
        Map<String, List<Double>> students = new LinkedHashMap<>();
        //Map<String, Double> students = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            double grade = Double.parseDouble(sc.nextLine());

            if (students.containsKey(name)) {
                //students.put(name, students.get(name) + grade);
                students.get(name).add(grade);
            } else {
                //students.put(name, grade);
                students.put(name, new ArrayList<>());
                students.get(name).add(grade);
            }
        }

        Map<String, Double> s = new LinkedHashMap<>();
        for (var entry : students.entrySet()) {
            double sum = entry.getValue().stream().mapToDouble(f -> f).sum();
            if(!s.containsKey(entry.getKey())){
                s.put(entry.getKey(), sum/entry.getValue().size());
            }
        }
        for (var entry : s.entrySet()) {
            if (entry.getValue() >= 4.5) System.out.println(entry.getKey() + " -> " + String.format("%.2f", entry.getValue()));
        }
        s.entrySet()
                .stream().filter(d -> d.getValue() >= 4.5)
                .forEach(entry -> System.out.println(entry.getKey() + " -> " + String.format("%.2f", entry.getValue())));
    }
    private int printNull(Map.Entry<String, Integer> entry) {
        if (entry.getValue() == null) return 0;
        else return entry.getValue();
    }
    private int printZero(Map<String, Integer> m, String s) {
        if (m.get(s) == null) return 0;
        else return m.get(s);
    }
    public void legendaryFarming() {
        Map<String, Integer> materials = new LinkedHashMap<>();

        while (true) {
            String[] s = sc.nextLine().toLowerCase().split("\\s+");
            boolean isReached = false;
            for (int i = 0; i < s.length; i++) {
                if (i % 2 == 0) {
                    if (materials.containsKey(s[i+1])) {
                        materials.put(s[i+1], materials.get(s[i+1]) + Integer.parseInt(s[i]));
                    } else {
                        materials.put(s[i+1], Integer.parseInt(s[i]));
                    }
                }
                if ((materials.containsKey("shards") && materials.get("shards") >= 250) ||
                        (materials.containsKey("fragments") && materials.get("fragments") >= 250) ||
                        (materials.containsKey("motes") && materials.get("motes") >= 250)  ) {
                    isReached = true;
                    break;
                }
            }
            if(isReached) break;

        }

        if (materials.containsKey("shards") && materials.get("shards") >= 250) {
            System.out.println("Shadowmourne obtained!");
            materials.put("shards", materials.get("shards") - 250);
        }
        else if (materials.containsKey("motes") && materials.get("motes") >= 250  ) {
            System.out.println("Dragonwrath obtained!");
            materials.put("motes", materials.get("motes") - 250);
        }
        else if (materials.containsKey("fragments") && materials.get("fragments") >= 250) {
            System.out.println("Valanyr obtained!");
            materials.put("fragments", materials.get("fragments") - 250);
        }

        System.out.println("shards: " + printZero(materials,"shards"));
        materials.remove("shards");
        System.out.println("fragments: " + printZero(materials,"fragments"));
        materials.remove("fragments");
        System.out.println("motes: " + printZero(materials,"motes"));
        materials.remove("motes");

        if(!materials.isEmpty()) materials.entrySet()
                .stream().forEach(entry -> System.out.println(entry.getKey() + ": " + printNull(entry)));

    }

    public void companyUsers() {
        Map<String, List<String>> employees = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("End")) break;

            String[] token = s.split(" -> ");

            if(employees.containsKey(token[0])) {
                if (!employees.get(token[0]).contains(token[1])) {
                    employees.get(token[0]).add(token[1]);
                }
            } else {
                employees.put(token[0], new ArrayList<>());
                employees.get(token[0]).add(token[1]);
            }
        }

        employees.entrySet()
                .forEach(entry -> {
                    System.out.printf("%s%n", entry.getKey());
                    entry.getValue().stream().
                            forEach(e -> System.out.printf("-- %s%n", e));
                });
    }
    private boolean userExist(Map<String, List<String>> input, String s) {
        for (var entry : input.entrySet()) {
            for (String e : entry.getValue()) {
                if (e.equals(s)) return true;
            }
        }
        return false;
    }
    private String getKeyToRemove(Map<String, List<String>> input, String s) {
        for (var entry : input.entrySet()) {
            for (String e : entry.getValue()) {
                if (e.equals(s)) return entry.getKey();
            }
        }
        return null;
    }
    public void forceBook() {
        Map<String, List<String>> forces = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("Lumpawaroo")) break;


            if (s.contains("|")) {
                String[] t = s.split(" [|] ");
                if (!forces.containsKey(t[0])) {
                    if (!userExist(forces, t[1])) {
                        forces.put(t[0], new ArrayList<>());
                        forces.get(t[0]).add(t[1]);
                    }
                } else if (forces.containsKey(t[0]) && !userExist(forces, t[1])){
                    forces.get(t[0]).add(t[1]);
                }
            } else if (s.contains("->")) {
                String[] t = s.split(" -> ");
                if (!forces.containsKey(t[1])) {
                    if (!userExist(forces, t[0])) {
                        forces.put(t[1], new ArrayList<>());
                        forces.get(t[1]).add(t[0]);
                        System.out.println(t[0] + " joins the " + t[1] + " side!");
                    }
                    else {
                        forces.get(getKeyToRemove(forces, t[0])).remove(t[0]);
                        forces.put(t[1], new ArrayList<>());
                        forces.get(t[1]).add(t[0]);
                        System.out.println(t[0] + " joins the " + t[1] + " side!");
                    }
                } else {
                    if (!userExist(forces, t[0])) {
                        forces.get(t[1]).add(t[0]);
                        System.out.println(t[0] + " joins the " + t[1] + " side!");
                    } else if (userExist(forces, t[0]) && !getKeyToRemove(forces, t[0]).equals(t[1])) {
                        forces.get(getKeyToRemove(forces, t[0])).remove(t[0]);
                        forces.get(t[1]).add(t[0]);
                        System.out.println(t[0] + " joins the " + t[1] + " side!");
                    }
                }
            }
        }
        forces.entrySet().stream().filter(s -> !s.getValue().isEmpty())
                .forEach(entry -> {
                    System.out.printf("Side: %s, Members: %d%n", entry.getKey(), entry.getValue().size());
                    entry.getValue().stream().
                            forEach(e -> System.out.printf("! %s%n", e));
                });
    }

    public void softuniExamResults() {
        Map<String, Integer> students = new LinkedHashMap<>();
        Map<String, Integer> submissions = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if(s.equals("exam finished")) break;

            String[] t = s.split("-");

            if (t[1].equals("banned")) {
              students.remove(t[0]);
            }

            if(t.length == 3) {
                if(students.containsKey(t[0])) {
                    if(students.get(t[0]) < Integer.parseInt(t[2])) {
                        students.put(t[0], Integer.parseInt(t[2]));
                    }
                } else {
                    students.put(t[0], Integer.parseInt(t[2]));
                }

                if(submissions.containsKey(t[1])) {
                    submissions.put(t[1], submissions.get(t[1]) + 1);
                } else {
                    submissions.put(t[1], 1);
                }
            }
        }

        System.out.println("Results:");
        students.entrySet().stream().
                forEach(entry -> System.out.printf("%s | %d%n", entry.getKey(), entry.getValue()));

        System.out.println("Submissions:");
        submissions.entrySet().stream().
                forEach(entry -> System.out.printf("%s - %d%n", entry.getKey(), entry.getValue()));
    }
    public static void main(String[] args) {
        Exercises e = new Exercises();
        e.softuniExamResults();
    }
}
