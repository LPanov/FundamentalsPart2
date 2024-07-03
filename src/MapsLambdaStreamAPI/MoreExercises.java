package MapsLambdaStreamAPI;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MoreExercises {
    Scanner sc = new Scanner(System.in);

    private void printBestCandidate(Map<String, Map<String, Integer>> candidates) {
        String best = "";
        int max = 0;
        for (var entry : candidates.entrySet()) {
            int total = 0;
            String temp = entry.getKey();
            for (var ent : entry.getValue().entrySet()) {
                total += ent.getValue();
            }
            if (total > max) {
                max = total;
                best = temp;
            }
        }
        System.out.println("Best candidate is " + best + " with total " + max + " points.");
    }

    public void ranking() {
        Map<String, String> contests = new TreeMap<>();
        Map<String, Map<String, Integer>> candidates = new TreeMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("end of contests")) break;

            String[] t = s.split(":");

            contests.put(t[0], t[1]);
        }

        while (true) {
            String s = sc.nextLine();
            if (s.equals("end of submissions")) break;

            String[] t = s.split("=>");
            String name = t[2];
            String course = t[0];
            String pass = t[1];
            int points = Integer.parseInt(t[3]);

            if (contests.containsKey(course) && contests.get(course).equals(pass)) {
                if (!candidates.containsKey(name)) {
                    candidates.put(name, new TreeMap<>());
                    candidates.get(name).put(course, points);
                } else {
                    if (candidates.get(name).containsKey(course) && candidates.get(name).get(course) < points) {
                        candidates.get(name).put(course, points);
                    } else if (!candidates.get(name).containsKey(course)) {
                        candidates.get(name).put(course, points);
                    }
                }
            }
        }

        printBestCandidate(candidates);
        System.out.println("Ranking:");
        candidates.entrySet().stream()      //принтиране на мап, в който има мап и подреждаж вътрешния мап по размер в нисходящ ред
                .forEach(entry -> {
                    System.out.println(entry.getKey());
                    entry.getValue().entrySet().stream()
                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                            .forEach(ent -> System.out.println("#  " + ent.getKey() + " -> " + ent.getValue()));
                });
    }

    private void printIndStats(Map<String, Map<String, Integer>> inputs) {
        Map<String, Integer> standings = new TreeMap<>();
        for (var entry : inputs.entrySet()) {
            for (var ent : entry.getValue().entrySet()) {
                String name = ent.getKey();
                int points = ent.getValue();

                if (standings.containsKey(name)) {
                    standings.put(name, standings.get(name) + points);
                } else {
                    standings.put(name, points);
                }
            }
        }

        AtomicInteger num = new AtomicInteger();//когато трябва преброител при принтиране, се добавя такъв инт
        num.set(1);
        standings.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(entry -> {
                    System.out.println(num.getAndIncrement() + ". " + entry.getKey() + " -> " + entry.getValue());
                });
    }

    public void judge() {
        Map<String, Map<String, Integer>> contests = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("no more time")) break;

            String[] t = s.split(" -> ");

            String name = t[0];
            String contest = t[1];
            int points = Integer.parseInt(t[2]);

            if (contests.containsKey(contest)) {
                if (contests.get(contest).containsKey(name)) {
                    if (contests.get(contest).get(name) < points) {
                        contests.get(contest).put(name, points);
                    }
                } else {
                    contests.get(contest).put(name, points);
                }
            } else {
                contests.put(contest, new TreeMap<>());
                contests.get(contest).put(name, points);
            }
        }
        AtomicInteger num = new AtomicInteger(1);
        contests.entrySet().stream()
                .forEach(entry -> {
                    num.set(1);
                    System.out.println(entry.getKey() + ": " + entry.getValue().size() + " participants");
                    entry.getValue().entrySet().stream()
                            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                            .forEach(ent -> {
                                int i;
                                System.out.println(num.getAndIncrement() + ". " + ent.getKey() + " <::> " + ent.getValue());
                            });
                });
        System.out.println("Individual standings:");
        printIndStats(contests);
    }

    public void MODAChallenger() {
        Map<String, Map<String, Integer>> players = new LinkedHashMap<>();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("Season end")) break;

            if (s.contains("->")) {
                String[] t = s.split(" -> ");
                String player = t[0];
                String position = t[1];
                int skill = Integer.parseInt(t[2]);

                LinkedHashMap<String, Integer> current = new LinkedHashMap<>();
                current.put(position, skill);
                if (!players.containsKey(player)) {
                    players.put(player, current);
                } else {
                    if (!players.get(player).containsKey(position)) {
                        players.get(player).put(position, skill);
                    } else {
                        if (players.get(player).get(position) < skill) {
                            players.get(player).put(position, skill);
                        }
                    }
                }
            }
            else if (s.contains(" vs ")) {
                String[] tokens = s.split(" vs ");
                String player1 = tokens[0];
                String player2 = tokens[1];
                if (players.containsKey(player1) && players.containsKey(player2)) {
                    boolean hasCommon = false;
                    for (String pl : players.get(player1).keySet()) {
                        for (String s1 : players.get(player2).keySet()) {
                            if (pl.equals(s1)) {
                                hasCommon = true;
                            }
                        }
                    }
                    if (hasCommon) {
                        if (players.get(player1).values().stream().mapToInt(i -> i).sum() >
                                players.get(player2).values().stream().mapToInt(i -> i).sum()) {
                            players.remove(player2);
                        } else if (players.get(player1).values().stream().mapToInt(i -> i).sum() <
                                players.get(player2).values().stream().mapToInt(i -> i).sum()) {
                            players.remove(player1);
                        }
                    }
                }
            }
        }

        players.entrySet().stream()
                .sorted((p1, p2) -> {
                    int result = Integer.compare(p2.getValue().values().stream().mapToInt(i -> i).sum(),
                            p1.getValue().values().stream().mapToInt(i -> i).sum());
                    if (result == 0) {
                        result = p1.getKey().compareTo(p2.getKey());
                    }
                    return result;
                })
                .forEach(entry -> {
                    System.out.printf("%s: %d skill%n", entry.getKey(), entry.getValue().values().stream().mapToInt(i -> i).sum());
                    entry.getValue()
                            .entrySet()
                            .stream()
                            .sorted((e1, e2) -> {
                                int res = Integer.compare(e2.getValue(), e1.getValue());
                                if (res == 0) {
                                    res = e1.getKey().compareTo(e2.getKey());
                                }
                                return res;
                            }).forEach(e -> {
                                System.out.printf("- %s <::> %d%n", e.getKey(), e.getValue());
                            });
                });
    }

    public void snowWhite() {
        Map<String, LinkedHashMap<String, Integer>> dwarfs = new LinkedHashMap<>();
        String input;

        while (!"Once upon a time".equals(input = sc.nextLine())) {

            String[] lineInput = input.split(" <:> ");

            String name = lineInput[0];
            String hatColor = lineInput[1];
            int physics = Integer.parseInt(lineInput[2]);

            dwarfs.putIfAbsent(hatColor, new LinkedHashMap<>());
            dwarfs.get(hatColor).putIfAbsent(name, physics);

            if (dwarfs.get(hatColor).get(name) < physics) {
                dwarfs.get(hatColor).put(name, physics);
            }
        }


        Map<String, Integer> print = new LinkedHashMap<>();

        for(Map.Entry<String, LinkedHashMap<String, Integer>> entry : dwarfs.entrySet()) {
            for(Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
                print.put(entry.getKey() + " " + subEntry.getKey() + " " + entry.getValue().size(), subEntry.getValue());
            }
        }
        print.entrySet().stream().sorted((pair2, pair1) -> {
            int sort = Integer.compare(pair1.getValue(), pair2.getValue());
            if (sort == 0) {
                String[] sort1 = pair1.getKey().split(" ");
                String[] sort2 = pair2.getKey().split(" ");
                sort = sort1[2].compareTo(sort2[2]);
            }
            return sort;
        }).forEach(pair -> {
            String[] printing = pair.getKey().split(" ");
            System.out.printf("(%s) %s <-> %d\n", printing[0], printing[1], pair.getValue());
        });

        /*(e1, e2) -> {
                    int res = Integer.compare(e2.getValue().values().stream().mapToInt(i -> i).sum(), e1.getValue().values().stream().mapToInt(i -> i).sum());
                    if (res == 0) {
                        res = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    }
                    return res;*/
        /*Comparator.comparingInt((Map.Entry<String, Map<String, Integer>> e) -> e.getValue().values().stream().mapToInt(i -> i).sum())
                        .thenComparingInt(e -> e.getValue().size()).reversed())
                        */

    }

    public static void main (String[]args){
        MoreExercises me = new MoreExercises();
        me.snowWhite();
    }
}
