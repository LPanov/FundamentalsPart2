package RegularExpressions;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.regex.MatchResult;

public class Exercises {
    Scanner sc = new Scanner(System.in);

    public void furniture() {
        String regex = ">>(?<furniture>[\\w]+)<<(?<price>[\\d]+[.]?[\\d]+)!(?<quantity>[\\d]+)";
        //List<String> names = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        double total = 0;

        Pattern pattern = Pattern.compile(regex);


        while (true) {
            String s = sc.nextLine();
            if (s.equals("Purchase")) break;

            str.append(s);
        }

        Matcher matchFurniture = pattern.matcher(str);

        System.out.println("Bought furniture:");
        while (matchFurniture.find()) {
            System.out.println(matchFurniture.group("furniture"));
            double price = Double.parseDouble(matchFurniture.group("price"));
            int quantity = Integer.parseInt(matchFurniture.group("quantity"));
            total += price*quantity;
        }
        System.out.println("Total money spend: " + String.format("%.2f", total));
    }

    public void softuniBarIncome() {
        String regex = "%(?<customer>[A-Z][a-z]*)%[^|$%.]*?<(?<product>\\w+)>[^|$%.]*?\\|(?<count>\\d+)[^|$%.]*?\\|[^|$%.]*?(?<price>[0-9]+(\\.[0-9]+)?)\\$";

        double total = 0;
        Pattern pattern = Pattern.compile(regex);

        StringBuilder str = new StringBuilder();
        while (true) {
            String s = sc.nextLine();
            if (s.equals("end of shift")) break;

            str.append(s);
        }

        Matcher matchCustomer = pattern.matcher(str);
        while (matchCustomer.find()) {
            String name = matchCustomer.group("customer");
            String product = matchCustomer.group("product");
            double totalPrice = Integer.parseInt(matchCustomer.group("count"))*Double.parseDouble(matchCustomer.group("price"));

            System.out.printf("%s: %s - %.2f%n", name, product, totalPrice);
            total += totalPrice;
        }
        System.out.println("Total income: " + String.format("%.2f",total));
    }
    private int getShifter(String s) {
        int shift = 0;
        for (char c : s.toCharArray()) {
            c = Character.toUpperCase(c);
            if (c == 'S' || c=='T' || c == 'A' || c == 'R') {
                shift++;
            }
        }
        return  shift;
    }
    private StringBuilder decrypt(String s, int shift) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : s.toCharArray()){
            decrypted.append((char) ((int) c - shift));
        }
        return decrypted;
    }
    public void starEnigma() {

        int n = Integer.parseInt(sc.nextLine());
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            int shift = getShifter(s);

            decrypted.append(decrypt(s, shift));
        }

        String regex = "@(?<name>[a-zA-Z]+)[^@\\-!:>]*?:[^@\\-!:>]*?(?<population>\\d+)[^@\\-!:>]*?![^@\\-!:>]*?(?<type>[AD])[^@\\-!:>]*?![^@\\-!:>]*?->[^@\\-!:>]*?(?<soldiers>\\d+)[^@\\-!:>]*?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matchMessage = pattern.matcher(decrypted);

        List<String> atacked = new ArrayList<>();
        List<String> destroyed = new ArrayList<>();

        while (matchMessage.find()) {
            if (matchMessage.group("type").equals("A")) {
                atacked.add(matchMessage.group("name"));
            } else if (matchMessage.group("type").equals("D")) {
                destroyed.add(matchMessage.group("name"));
            }
        }
        System.out.println("Attacked planets: " + atacked.size());
        atacked.stream().sorted().forEach(s -> System.out.println("-> " + s));
        System.out.println("Destroyed planets: " + destroyed.size());
        destroyed.stream().sorted().forEach(s -> System.out.println("-> " + s));
    }


    public void netherRealm() {
        List<String> daemons = Arrays.stream(sc.nextLine().split(",\\s*"))
                .map(String::trim)
                .collect(Collectors.toList());

        Pattern healthPattern = Pattern.compile("[^-+*/.\\d]");
        Pattern damagePattern = Pattern.compile("[-+]?\\d+(\\.\\d+)?");
        Pattern damageModifiersPattern = Pattern.compile("[*/]");

        for (String daemonName : daemons) {
            int daemonHealth = healthPattern.matcher(daemonName)
                    .results()
                    .mapToInt(m -> m.group().charAt(0))
                    .sum();

            double daemonDamage = damagePattern.matcher(daemonName)
                    .results()
                    .map(MatchResult::group)
                    .mapToDouble(Double::parseDouble)
                    .sum();

            Matcher matcher = damageModifiersPattern.matcher(daemonName);
            while (matcher.find()) {
                if (matcher.group().equals("*")) {
                    daemonDamage *= 2.0;
                } else {
                    daemonDamage /= 2.0;
                }
            }

            System.out.printf("%s - %d health, %.2f damage%n", daemonName, daemonHealth, daemonDamage);
        }
    }

    public void extractEmails() {
        String s = sc.nextLine();

        String regex = "\\b(?<user>\\w+[._-]*\\w*)@(?<host>[a-z]+([.-]+\\w+)*)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matchEmail = pattern.matcher(s);
        matchEmail.results().map(MatchResult::group).forEach(System.out::println);
    }

    public void validPassword() {
        String regexPassword = "_\\.+(?<passwordText>[A-Z][A-Za-z0-9]{4,}[A-Z])_\\.+";
        Pattern pattern = Pattern.compile(regexPassword);

        int n = Integer.parseInt(sc.nextLine()); //брой пароли
        for (int count = 1; count <= n; count++) {
            String password = sc.nextLine();
            //1. проверка дали е валидна паролата
            Matcher matcher = pattern.matcher(password);
            //1. matcher = [] -> невалидна парола
            //2. matcher = ["_.A123f23A_."] -> валидна парола

            if (matcher.find()) {
                //matcher.find() -> "_.A123f23A_."
                //имаме валидна парола -> категоризирам
                String textPassword = matcher.group("passwordText"); //"A123f23A"
                //2. категоризация
                StringBuilder sbDigits = new StringBuilder(); //долепям намерените цифри в паролата
                for (char symbol : textPassword.toCharArray()) {
                    if (Character.isDigit(symbol)) {
                        //цифра
                        sbDigits.append(symbol);
                    }
                }
                //sbDigits -> долепени всички цифри в паролата
                //1. нямаме цифри в паролата -> група default
                if (sbDigits.length() == 0) {
                    System.out.println("Group: default");
                }
                //2. имаме цифри в паролата
                else {
                    System.out.println("Group: " + sbDigits);
                }
            } else {
                //matcher = [] -> невалидна парола
                System.out.println("Invalid pass!");
            }

        }

    }


    public static void main(String[] args) {
        Exercises e = new Exercises();
        e.validPassword();
    }
}
