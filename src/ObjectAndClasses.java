import java.util.Random;
import java.util.Scanner;
import java.math.BigInteger;

public class ObjectAndClasses {
    public Scanner scanner = new Scanner(System.in);

    public void randomizeWords() {
        String s = scanner.nextLine();
        String[] words = s.split(" ");
        Random rnd = new Random();
        for (int i = 0; i < words.length; i++) {
            int pos = rnd.nextInt(words.length);
            String temp = words[i];
            words[i] = words[pos];
            words[pos] = temp;
        }
        for (String word : words) {
            System.out.println(word);
        }
    }

    public void SumBigNumbers() {
        BigInteger first = new BigInteger(scanner.nextLine());
        BigInteger second = new BigInteger(scanner.nextLine());
        BigInteger sum = first.add(second);

        System.out.println(sum);
    }
    private BigInteger factorial(int n) {
        BigInteger bi = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            bi = bi.multiply(BigInteger.valueOf(i));
        }
        return bi;
    }
    public void BigFactorial() {
        int N = Integer.parseInt(scanner.nextLine());
        System.out.println(factorial(N));
    }
    public static void main(String[] args) {
        ObjectAndClasses oc = new ObjectAndClasses();
        oc.randomizeWords();
    }
}
