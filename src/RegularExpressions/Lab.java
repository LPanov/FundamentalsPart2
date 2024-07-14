package RegularExpressions;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class Lab {
    Scanner sc = new Scanner(System.in);

    public void matchFullName() {

        String regex ="\\b[A-Z][a-z]+ [A-Z][a-z]+\\b";
        String names = sc.nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(names);

        while (matcher.find()) {
            System.out.print(matcher.group() + " ");
        }

    }

    public void matchPhoneNumber() {
        String regex = "\\+359([- 0])2\\1[\\d]{3}\\1[\\d]{4}\\b";
        String phones = sc.nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher phoneMather = pattern.matcher(phones);

        List<String> matchedPhones = new LinkedList<>();

        while (phoneMather.find()) {
            matchedPhones.add(phoneMather.group());
        }

        System.out.println(String.join(", ", matchedPhones));
    }

    public void matchDates() {
        String regex = "\\b(?<day>\\d{2})([-.\\/])(?<month>[A-Z][a-z]{2})\\2(?<year>[\\d]{4})\\b";
        String date = sc.nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher dates = pattern.matcher(date);

        while (dates.find()) {

            String day = dates.group("day");
            String month = dates.group("month");
            String year = dates.group("year");

            System.out.printf("Day: %s, Month: %s, Year: %s%n", day, month, year);
        }
    }

    public static void main(String[] args) {
        Lab l = new Lab();
        l.matchDates();
    }
}
