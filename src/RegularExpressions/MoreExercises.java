package RegularExpressions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoreExercises {
    Scanner sc = new Scanner(System.in);

    public void winningTicket() {
        String[] tickets = sc.nextLine().split("\\s*,\\s*");
        String regex = ".*(?<symbol>@{6}|#{6}|\\${6}|\\^{6}).*\\1.*";
        Pattern pattern = Pattern.compile(regex);

        for (String ticket : tickets) {
            Matcher matcher = pattern.matcher(ticket);
            if (ticket.length() == 20) {
                if (matcher.find()) {
                    String symbolGroup = matcher.group("symbol");
                    String secondPart = ticket.substring(ticket.length()-10);
                    if(!secondPart.contains(symbolGroup)){
                        System.out.printf("ticket \"%s\" - no match%n", ticket);
                        continue;
                    }
                    int leftSideLongestSequence = 0;
                    int rightSideLongestSequence = 0;
                    int currentSequence = 1;

                    for (int i = 1; i < 10; i++) {
                        if (ticket.charAt(i) == ticket.charAt(i - 1)) {
                            currentSequence++;
                        } else {
                            currentSequence = 1;
                        }

                        if (currentSequence > leftSideLongestSequence) {
                            leftSideLongestSequence = currentSequence;
                        }
                    }

                    currentSequence = 1;

                    for (int i = 10; i < 20; i++) {
                        if (ticket.charAt(i) == ticket.charAt(i - 1)) {
                            currentSequence++;
                        } else {
                            currentSequence = 1;
                        }

                        if (currentSequence > rightSideLongestSequence) {
                            rightSideLongestSequence = currentSequence;
                        }
                    }

                    int sideWithSmallerSequence = Math.min(leftSideLongestSequence, rightSideLongestSequence);
                    char matchSymbol = matcher.group("symbol").charAt(0);
                    if (sideWithSmallerSequence >= 6 && sideWithSmallerSequence <= 9) {
                        System.out.printf("ticket \"%s\" - %d%c%n", ticket, sideWithSmallerSequence, matchSymbol);
                    } else if (sideWithSmallerSequence == 10) {
                        System.out.printf("ticket \"%s\" - %d%c Jackpot!%n", ticket, sideWithSmallerSequence, matchSymbol);
                    }

                } else {
                    System.out.printf("ticket \"%s\" - no match%n", ticket);
                }
            } else {
                System.out.println("invalid ticket");
            }

        }
    }

    public void rageQuit() {
        String message = sc.nextLine();

        String regex = "(?<word>[\\D]+)(?<numbers>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        StringBuilder out = new StringBuilder();

        while (matcher.find()) {
            int n = Integer.parseInt(matcher.group("numbers"));
            if (n > 0 ) {
                for (int i = 0; i < n; i++) {
                    out.append(matcher.group("word").toUpperCase());
                }
                //out.repeat(out.append(matcher.group("word").toUpperCase()), n);
            }
        }

        System.out.println("Unique symbols used: " + out.chars().distinct().count());
        System.out.println(out);
    }

    public void postOffice() {
        String[] input = sc.nextLine().split("\\|");

        String firstPart = input[0];
        String secondPart = input[1];
        String thirdPart = input[2];
        LinkedHashMap<Character, Integer> letters = new LinkedHashMap<>();

        Pattern patternText = Pattern.compile("([#$%*&])([A-Z]+)\\1");
        Matcher matcherText = patternText.matcher(firstPart);

        String capitalLetters = "";
        while (matcherText.find()) {
            String current = matcherText.group();
            capitalLetters = current.substring(1, current.length() - 1);
        }

        for (int i = 0; i < capitalLetters.length(); i++) {
            char symbol = capitalLetters.charAt(i);

            Pattern patternDigits = Pattern.compile("([0-9]{2}):([0-9]{2})");
            Matcher matcherDigits = patternDigits.matcher(secondPart);

            while (matcherDigits.find()) {
                String current = matcherDigits.group();
                String[] digits = current.split(":");
                int asciiCode = Integer.parseInt(digits[0]);
                int length = Integer.parseInt(digits[1]);

                if (symbol == asciiCode && !letters.containsKey(symbol)) {
                    letters.put(symbol, length + 1);
                    break;
                }
            }
        }

        String[] thirdText = thirdPart.split("\\s+");
        for (int i = 0; i < capitalLetters.length(); i++) {

            char firstLetter = capitalLetters.charAt(i);
            int length = letters.get(firstLetter);

            for (String word : thirdText) {

                int lengthWord = word.length();
                String wordFirstChar = word.substring(0, 1);
                char firstChar = wordFirstChar.charAt(0);

                if (lengthWord == length && firstLetter == firstChar) {
                    System.out.println(word);
                }
            }
        }
    }

    private String decryptMessage(String s, int n) {
        StringBuilder out = new StringBuilder();
        for (char c : s.toCharArray()) {
            out.append((char) ((int) c - n));
        }
        return out.toString();
    }
    public void santasSecretHelper() {
        int n = Integer.parseInt(sc.nextLine());

        String regex = "(?<name>@[A-Za-z]+)[^@\\-!:>]*(?<behavior>!G!)";
        Pattern pattern = Pattern.compile(regex);

        List<String> names = new ArrayList<>();

        while (true) {
            String message = sc.nextLine();
            if (message.equals("end")) break;
            String decrypted = decryptMessage(message, n);
            Matcher matcher = pattern.matcher(decrypted);

            while (matcher.find()) {
                if (matcher.group("behavior").equals("!G!")) {
                    names.add(matcher.group("name").substring(1));
                }
            }
        }

        names.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        MoreExercises me = new MoreExercises();
        me.rageQuit();
    }
}
