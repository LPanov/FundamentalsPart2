package MapsLambdaStreamAPI;

import java.text.DecimalFormat;
import java.util.*;

public class AssociativeArraysLab {
    Scanner sc = new Scanner(System.in);

    public void countRealNumbers() {
        /*List<Integer> numbers = Arrays.stream(sc.next().split("\\s+"))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());*/
        double[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble)
                .toArray();

        TreeMap<Double, Integer> counts = new TreeMap<>();
        for (double num:nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 0);
            }
            counts.put(num, counts.get(num) + 1);
        }

        for (Map.Entry<Double, Integer> entry : counts.entrySet()) {
            DecimalFormat df = new DecimalFormat("#.#######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
    public void wordSynonyms() {
        LinkedHashMap<String, ArrayList<String>> words = new LinkedHashMap<>();
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            String synonym = sc.nextLine();

            words.putIfAbsent(word, new ArrayList<>());
            words.get(word).add(synonym);
        }
        for (Map.Entry<String, ArrayList<String>> entry : words.entrySet()) {
            System.out.println(entry.getKey() + " - " + String.join(", ", entry.getValue()));
        }
    }
    public void oddOcccurrences() {
        String[] words = sc.nextLine().split("\\s+");
        LinkedHashMap<String, Integer> counts = new LinkedHashMap<>();

        for (String word : words) {
            String wordLower = word.toLowerCase();
            if (counts.containsKey(wordLower)) {
                counts.put(wordLower, counts.get(wordLower) + 1);
            } else {
                counts.put(wordLower, 1);
            }
        }


        ArrayList<String> odds = new ArrayList<>();
        for (var entry : counts.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                odds.add(entry.getKey());
            }
        }
        System.out.println(String.join(", ", odds));

    }

    public void wordFilter() {
        String[] words = Arrays.stream(sc.nextLine().split(" "))
                        .filter(w -> w.length() % 2 ==0)
                        .toArray(String[]::new);
        for (String word : words) {
            System.out.println(word);
        }
    }
    public static void main(String[] args) {
        AssociativeArraysLab aa = new AssociativeArraysLab();
        aa.wordFilter();
    }
}
