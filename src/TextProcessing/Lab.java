package TextProcessing;

import java.util.Scanner;

public class Lab {
    Scanner sc = new Scanner(System.in);

    public void reverseStrings() {
        while (true) {
            String s = sc.nextLine();
            if (s.equals("end")) break;

            StringBuilder reversed = new StringBuilder(s);
            System.out.println(s + " = " + reversed.reverse());
        }
    }

    public void repeatString() {
        String[] input = sc.nextLine().split(" ");
        StringBuilder out = new StringBuilder();
        for (String s : input) {
            out.append(s.repeat(s.length()));
        }

        System.out.println(out);
    }

    public void substring() {
        String eraser = sc.nextLine();
        String word = sc.nextLine();

        StringBuilder out = new StringBuilder(word);

        while (word.contains(eraser)) {
            word = word.replace(eraser, "");
        }

        System.out.println(word);
    }

    public void textFilter() {
        String[] banWords = sc.nextLine().split(", ");
        String s = sc.nextLine();

        for (int i = 0; i < banWords.length; i++) {
            if (s.contains(banWords[i])) {
                String censored = "*".repeat(banWords[i].length());
                s = s.replace(banWords[i], censored);
            }
        }

        System.out.println(s);
    }

    public void digitsLettersAndOthers() {
        String s = sc.nextLine();

        String digits = "";
        String letters = "";
        String others = "";

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                digits += s.charAt(i);
            }
            else if (Character.isLetter(s.charAt(i))) {
                letters += s.charAt(i);
            }
            else {
                others += s.charAt(i);
            }
        }

        System.out.println(digits);
        System.out.println(letters);
        System.out.println(others);
    }
    public static void main(String[] args) {
        Lab l = new Lab();
        l.digitsLettersAndOthers();
    }
}
