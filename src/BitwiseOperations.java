import java.util.Scanner;

public class BitwiseOperations {
    Scanner sc = new Scanner(System.in);

    public void binaryDigitCount() {
        int n = Integer.parseInt(sc.nextLine());
        char b = sc.nextLine().charAt(0);
        int count = 0;

        String binary = Integer.toBinaryString(n);
        for (char c : binary.toCharArray()) {
            if (c == b) count++;
        }

        System.out.println(count);
    }

    public void bitPosition() {
        int n = Integer.parseInt(sc.nextLine());
        String b = Integer.toBinaryString(n);

        System.out.println(b.charAt(b.length()-2));
    }
    public static void main(String[] args) {
        BitwiseOperations bo = new BitwiseOperations();
        bo.bitPosition();
    }
}
