import java.util.Arrays;
import java.util.Scanner;

public class BitwiseOperations {
    Scanner sc = new Scanner(System.in);

    public void binaryDigitCount() {
        int n = Integer.parseInt(sc.nextLine());
        byte digit = Byte.parseByte(sc.nextLine());

        int count = 0;

        while (n != 0) {

            int leftover = n % 2;
            if (leftover == digit) {
                count++;
            }
            n = n / 2;
        }

        System.out.println(count);
    }

    public void bitPosition() {
        int n = Integer.parseInt(sc.nextLine());
        int mask = 1 << 1;
        int result = (n & mask) >> 1;
        System.out.println(result);
    }

    public void pThBit() {
        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        int mask = 1 << p;
        int result = (n & mask) >> p;

        System.out.println(result);
    }

    public void bitDestroyer() {
        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        int mask = ~(1 << p);
        int result = (n & mask);

        System.out.println(result);
    }

    public void oddTimes() {
        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int result = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            result = result^numbers[i];
        }

        System.out.println(result);
    }

    public void triBitSwitch() {
        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        int mask = 7 << p;
        int result = n ^ mask;
        System.out.println(result);
    }

    public static void main(String[] args) {
        BitwiseOperations bo = new BitwiseOperations();
        bo.triBitSwitch();
    }
}
