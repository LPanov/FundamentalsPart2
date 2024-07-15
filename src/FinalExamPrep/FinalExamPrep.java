package FinalExamPrep;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalExamPrep {
    Scanner sc = new Scanner(System.in);

    private String toUpperOrLower(String s, String type) {
        if (type.equals("Lower")) {
            s = s.toLowerCase();
        } else if (type.equals("Upper")) {
            s = s.toUpperCase();
        }
        System.out.println(s);
        return s;
    }
    private void reverse(String s, int start, int end) {
        StringBuilder rev = new StringBuilder();
        rev.append(s.substring(start, end+1));
        System.out.println(String.valueOf(rev.reverse()));
    }

    private String substring(String s, String cut) {
        if (s.contains(cut)) {
            s = s.replace(cut, "");
            System.out.println(s);
        } else {
            System.out.println("The username "+s+" doesn't contain "+cut+".");
        }
        return s;
    }
    private String replacce(String s, char r) {
        for (char c : s.toCharArray()) {
            if (c == r) s = s.replace(r, '-');
        }
        System.out.println(s);
        return s;
    }
    private void isValid(String s, char valid) {
        if (s.contains(String.valueOf(valid))) System.out.println("Valid username.");
        else System.out.println(valid + " must be contained in your username.");
    }

    public void registration() {
        String username = sc.nextLine();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("Registration")) break;

            String[] token = s.split(" ");

            if (token[0].equals("Letters")) {
                username = toUpperOrLower(username, token[1]);
            }
            else if (token[0].equals("Reverse")) {
                int start = Integer.parseInt(token[1]);
                int end = Integer.parseInt(token[2]);
                if (start >= 0 && end < username.length()) {
                    reverse(username, start, end);
                }
            }
            else if (token[0].equals("Substring")) {
                String cut = token[1];
                username = substring(username, cut);
            }
            else if (token[0].equals("Replace")) {
               username = replacce(username, token[1].charAt(0));
            }
            else if (token[0].equals("IsValid")) {
                isValid(username, token[1].charAt(0));
            }

        }
    }

    public void destinationMapper() {
        String s = sc.nextLine();

        String regex = "=[A-Z][a-zA-Z]{2,}=|/[A-Z][a-zA-Z]{2,}/";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(s);

        List<String> destinations = new ArrayList<>();

        matcher.results().map(MatchResult::group).forEach(m -> destinations.add(m.substring(1, m.length() - 1)));
        System.out.println("Destinations: "+String.join(", ", destinations));
        AtomicInteger points = new AtomicInteger();
        destinations.forEach(d -> points.addAndGet(d.length()));
        System.out.println("Travel Points: " + points);

    }

    public void bakeryShop() {
        Map<String, Integer> food = new LinkedHashMap<>();
        int sold = 0;

        while (true) {
            String s = sc.nextLine();
            if (s.equals("Complete")) break;

            String[] token = s.split(" ");

            if (token[0].equals("Receive")) {
                int quantity = Integer.parseInt(token[1]);
                String type = token[2];
                if (quantity > 0) {
                    if (food.containsKey(type)) food.put(type, food.get(type) + quantity);
                    else food.put(type, quantity);
                }
            }
            else if (token[0].equals("Sell")) {
                int quantity = Integer.parseInt(token[1]);
                String type = token[2];
                if (!food.containsKey(type)) {
                    System.out.println("You do not have any "+type+".");
                } else {
                    if (quantity > food.get(type)) {
                        sold += food.get(type);
                        System.out.println("There aren't enough "+type+". You sold the last "+food.get(type)+" of them.");
                        food.remove(type);
                    }
                    else {
                        sold += quantity;
                        System.out.println("You sold "+quantity+" "+type+".");
                        food.put(type, food.get(type) - quantity);
                        if (food.get(type) <= 0) food.remove(type);
                    }
                }
            }
        }

        food.entrySet().stream().forEach(entry -> System.out.printf("%s: %d%n", entry.getKey(), entry.getValue()));
        System.out.println("All sold: "+sold+" goods");
    }

    public static void main(String[] args) {
        FinalExamPrep fep = new FinalExamPrep();
        fep.bakeryShop();
    }
}
