import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalExam {
    Scanner sc = new Scanner(System.in);

    private String decrypt(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                output.append(Character.toLowerCase(c));
            } else {
                output.append(Character.toUpperCase(c));
            }
        }
        return output.toString();
    }
    public void galacticCodeDecryption() {
        String input = sc.nextLine();
        StringBuilder str = new StringBuilder(input);

        while (true) {
            String[] commands = sc.nextLine().split("\\s+");
            if (commands[0].equals("Finalize")) break;

            if (commands[0].equals("Encrypt")) {
                input = str.reverse().toString();
                System.out.println(input);
            }
            else if (commands[0].equals("Decrypt")) {
                input = decrypt(input);
                str = new StringBuilder(input);
                System.out.println(input);
            }
            else if (commands[0].equals("Substitute")) {
                String substr = commands[1];
                String replacement = commands[2];
                if (input.contains(substr)) {
                    input = input.replace(substr, replacement);
                    str = new StringBuilder(input);
                    System.out.println(input);
                } else {
                    System.out.println("Character not found.");
                }

            }
            else if (commands[0].equals("Scramble")) {
                int index = Integer.parseInt(commands[1]);
                String c = commands[2];

                if (index >= 0 && index < input.length()) {
                    input = str.replace(index, index+1, c).toString();
                    System.out.println(input);
                } else {
                    System.out.println("Index out of bounds.");
                }
            }
            else if (commands[0].equals("Remove")) {
                String remove = commands[1];
                input = input.replace(remove, "");
                str = new StringBuilder(input);
                System.out.println(input);
            }
            else {
                System.out.println("Invalid command detected!");
            }
        }
    }

    public void bossRush() {
        int n = Integer.parseInt(sc.nextLine());

        String regex = "\\|(?<name>[a-zA-Z]{4,})\\|:#(?<title>[a-zA-Z]+\\s[a-zA-Z]+)#";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            Matcher matcher = pattern.matcher(input);
            boolean match = false;

            while (matcher.find()) {
                match = true;
                String name = matcher.group("name");
                String title = matcher.group("title");
                System.out.printf("%s, The %s%n", name, title);
                System.out.printf(">> Strength: %d%n", name.length());
                System.out.printf(">> Armor: %d%n", title.length());
            }

            if (!match) System.out.println("Access denied!");
        }
    }

    public void followers() {
        Map<String, Integer> followers = new LinkedHashMap<>();

        while (true) {
            String[] token = sc.nextLine().split(": ");
            if (token[0].equals("Log out")) break;

            String username = token[1];
            //System.out.println(username);

            if (token[0].equals("New follower")) {
                followers.putIfAbsent(username, 0);
            }
            else if (token[0].equals("Like")) {
                int count = Integer.parseInt(token[2]);
                if (!followers.containsKey(username)) {
                    followers.put(username, count);
                } else {
                    followers.put(username, followers.get(username) + count);
                }
            }
            else if (token[0].equals("Comment")) {
                if (!followers.containsKey(username)) {
                    followers.put(username, 1);
                } else {
                    followers.put(username, followers.get(username) + 1);
                }
            }
            else if (token[0].equals("Blocked")) {
                if (followers.containsKey(username)) {
                    followers.remove(username);
                } else {
                    System.out.println(username+" doesn't exist.");
                }
            }
        }

        System.out.println(followers.size() + " followers");
        followers.entrySet().stream().forEach(f -> System.out.println(f.getKey() + ": " + f.getValue()));
    }

    public static void main(String[] args) {
        FinalExam fe = new FinalExam();
        fe.followers();


    }
}
