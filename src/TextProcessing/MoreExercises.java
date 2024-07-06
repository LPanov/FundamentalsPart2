package TextProcessing;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MoreExercises {
    Scanner sc = new Scanner(System.in);

    public void extractPersonInf() {
        int n = Integer.parseInt(sc.nextLine());
        String name = "";
        int age = 0;

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            name = s.substring(s.indexOf('@') + 1, s.indexOf('|'));
            age = Integer.parseInt(s.substring(s.indexOf('#') + 1, s.indexOf('*')));
            System.out.printf("%s is %d years old.%n", name, age);
        }
    }

    public void asciiSimulator() {
        int start = (int) sc.nextLine().charAt(0);
        int end = (int) sc.nextLine().charAt(0);
        String s = sc.nextLine();

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((int) c < end && (int) c > start) {
                sum += (int) c;
            }
        }

        System.out.println(sum);

    }

    public void treasureFinder() {
        int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        StringBuilder decrypted = new StringBuilder();
        List<String> messages = new ArrayList<>();

        while (true) {
            String s = sc.nextLine();
            if(s.equals("find")) break;

            decrypted.setLength(0);
            int k = 0;

            for (int i = 0; i < s.length(); i++) {
                if (k >= nums.length) k = 0;
                char dec = (char) ((int) s.charAt(i) - nums[k]);
                decrypted.append(dec);
                k++;
            }
            messages.add(String.valueOf(decrypted));
        }

        messages.stream().forEach(s -> {
            String type = s.substring(s.indexOf("&") + 1, s.lastIndexOf("&"));
            String cordinates = s.substring(s.indexOf("<") + 1, s.indexOf(">"));
            System.out.printf("Found %s at %s%n", type, cordinates);
        });

    }
    private char convertMorse(String morse) {
        char output = switch (morse) {
            case ".-" -> 'A'; case "-..." -> 'B'; case "-.-." -> 'C';
            case "-.." -> 'D'; case "..-." -> 'F'; case "--." -> 'G';
            case "...." -> 'H'; case ".." -> 'I'; case ".---" -> 'J';
            case "-.-" -> 'K'; case ".-.." -> 'L'; case "--" -> 'M';
            case "-." -> 'N'; case "---" -> 'O'; case ".--." -> 'P';
            case "--.-" -> 'Q'; case ".-." -> 'R'; case "..." -> 'S';
            case "-" -> 'T'; case "..-" -> 'U'; case "...-" -> 'V';
            case ".--" -> 'W'; case "-..-" -> 'X'; case "-.--" -> 'Y';
            case "--.." -> 'Z'; case "." -> 'E';
            default -> ' ';
        };

        return output;
    }
    public void morseCodeTranslator() {
        String[] morseCode = sc.nextLine().split(" ");
        StringBuilder message = new StringBuilder();

        for (String morse : morseCode) {
            message.append(convertMorse(morse));
        }

        System.out.println(message);
    }

    public void HTML() {
        String title = sc.nextLine();
        String content = sc.nextLine();
        List<String> comments = new ArrayList<>();

        while (true) {
            String comment = sc.nextLine();
            if (comment.equals("end of comments")) break;

            comments.add("<div>\n\t" + comment + "\n</div>");
        }
        System.out.println("<h1>\n\t" + title + "\n</h1>");
        System.out.println("<article>\n\t" + content + "\n</article>");
        comments.forEach(System.out::println);
    }
    public static void main(String[] args) {
        MoreExercises me = new MoreExercises();
        me.HTML();

    }
}
