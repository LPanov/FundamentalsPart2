package FinalExamPrep;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prep2 {
    public Scanner sc = new Scanner(System.in);

    public void theImitationGame() {
        String code = sc.nextLine();
        StringBuilder str = new StringBuilder(code);

        while (true) {
            String[] commands = sc.nextLine().split("\\|");
            if(commands[0].equals("Decode")) break;

            if (commands[0].equals("Move")) {
                int part = Integer.parseInt(commands[1]);
                if (part <= str.length() && part > 0) {
                    String move = str.substring(0, part);
                    str.replace(0, part, "");
                    str.append(move);
                    code = str.toString();
                }
            }
            else if (commands[0].equals("Insert")) {
                int index = Integer.parseInt(commands[1]);
                String insert = commands[2];
                if (index >= 0 && index <= str.length()) {
                    str.insert(index, insert);
                    code = str.toString();
                }

            }
            else if (commands[0].equals("ChangeAll")) {
                String substr = commands[1];
                String replacement = commands[2];
                code = code.replace(substr, replacement);
                str = new StringBuilder(code);
            }
            //System.out.println(str);
            //System.out.println(code);
        }

        System.out.println("The decrypted message is: " + str);
    }

    public void adAstra() {
        String input = sc.nextLine();
        String regex = "([#|])(?<name>\\w+\\s*\\w*)\\1(?<expDate>\\d{2}/\\d{2}/\\d{2})\\1(?<calories>\\d+)\\1";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        AtomicInteger total = new AtomicInteger();
        int days = 0;

        matcher.results().mapToInt(m -> Integer.parseInt(m.group("calories"))).forEach(f -> total.addAndGet(f));
        days = total.get() /2000;

        System.out.println("You have food to last you for: "+days+" days!");

        Matcher matcher1 = pattern.matcher(input);
        while (matcher1.find()) {
            System.out.printf("Item: %s, Best before: %s, Nutrition: %s%n", matcher1.group("name"), matcher1.group("expDate"), matcher1.group("calories"));
        }
    }


    public static void main(String[] args) {
        Prep2 p = new Prep2();
        p.adAstra();
    }
}
