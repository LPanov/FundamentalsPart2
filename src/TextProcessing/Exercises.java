package TextProcessing;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class Exercises {
    Scanner sc = new Scanner(System.in);
    private boolean isValid(String s) {
        return s.chars().allMatch(c -> Character.isLetterOrDigit(c) || c == '-' || c == '_');
    }
    public void validUsername() {
        String[] input = sc.nextLine().split(", ");
        List<String> valids = new ArrayList<>();

        for (String s : input) {
            if (s.length() >= 3 && s.length() <= 16 && isValid(s)) {
                valids.add(s);
            }
        }

        valids.forEach(System.out::println);

    }

    public void  characterMultiplier() {
        String[] input = sc.nextLine().split("\\s+");
        String word1 = (input[0].length() >= input[1].length()) ? input[0] : input[1];
        String word2 = (input[0].length() < input[1].length()) ? input[0] : input[1];

        int total = 0;
        for (int i = 0; i < word1.length(); i++) {
            try {
                total += (int) word1.charAt(i) * (int) word2.charAt(i);
            } catch (StringIndexOutOfBoundsException e) {
                total += word1.charAt(i);
            }

        }

        System.out.println(total);
    }

    public void extractFile() {
        String p = sc.nextLine();
        String separator = "\\";
        p = p.replaceAll(Pattern.quote(separator), "\\\\");
        String[] s = p.split("\\\\");
        String path = s[s.length -1];

        String file = path.substring(0, path.indexOf("."));
        String extension = path.substring(path.indexOf(".")+1);
        //System.out.println(path);
        System.out.println("File name: " + file);
        System.out.println("File extension: " + extension);
    }

    public void caesarCipher() {
        String s = sc.nextLine();
        StringBuilder out = new StringBuilder();

        for (char c : s.toCharArray()) {
            c = (char) ((int) c + 3);
            out.append(c);
        }

        System.out.println(out);
    }
    private boolean isNull(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Integer.parseInt(String.valueOf(c)) == 0) count++;
        }
        return count == s.length();
    }
    public void multiplyBigNumber() {
        String s = sc.nextLine().replaceFirst("^0+(?!$)", "");;
        int m = Integer.parseInt(sc.nextLine());
        String[] t = new String[s.length()];

        for (int i = 0; i < t.length; i ++) {
            t[i] = String.valueOf(Integer.parseInt(String.valueOf(s.charAt(i))) * m);
        }
        StringBuilder str = new StringBuilder();
        for (int i = t.length - 1; i >= 0; i--) {
            if (Integer.parseInt(t[i]) < 10) {
                str.insert(0, t[i]);
            } else if (Integer.parseInt(t[i]) >= 10 && i > 0) {
                str.insert(0, Integer.parseInt(t[i])%10);
                t[i - 1] = String.valueOf(Integer.parseInt(t[i - 1]) + Integer.parseInt(t[i])/10);
            } else if (i == 0 ) {
                str.insert(0, t[0]);
            }
        }

        if (m == 0) System.out.println(0);
        else if (isNull(s)) System.out.println(0);
        else System.out.println(str);
    }

    public void replaceRepeatingChars() {
        String s = sc.nextLine();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (i != s.length()-1 && s.charAt(i) != s.charAt(i+1)) {
                out.append(s.charAt(i));
            }
            else if (i == s.length() - 1) {
                out.append(s.charAt(i));
            }
        }

        System.out.println(out);
    }

    public void stringExplosion() {
        String s = sc.nextLine();
        List<Character> str = new ArrayList<>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            str.add(c);

            if (c == '>') {
                i++;
                int bomb = s.charAt(i) - '0';
                int j = i;
                for (; j < i + bomb && j < s.length(); j++) {
                    if (s.charAt(j) == '>') {
                        str.add('>');
                        j++;
                        bomb += (s.charAt(j) - '0') + 1;
                    }
                }

                i = j - 1;
            }
        }

        str.stream().forEach(System.out::print);
    }
    private int position(char c) {
        c = Character.toUpperCase(c);
        return (int) c - 64;
    }
    public void lettersChangeNumbers() {
        String[] str = sc.nextLine().split("\\s+");
        double sum = 0;
        for (String s : str) {
            char start = s.charAt(0);
            char end = s.charAt(s.length()-1);
            double num = Double.parseDouble(s.substring(1, s.length()-1));

            if (Character.isUpperCase(start)) {
                sum += num / position(start);
            } else {
                sum += num * position(start);
            }

            if (Character.isUpperCase(end)) {
                sum -= position(end);
            } else {
                sum += position(end);
            }
        }

        System.out.println(String.format("%.2f", sum));
    }
    public static void main(String[] args) {
        Exercises e = new Exercises();
        e.lettersChangeNumbers();
    }
}
