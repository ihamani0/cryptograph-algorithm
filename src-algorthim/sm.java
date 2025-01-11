import java.util.Scanner;

public class sm {

    public static long squareMultiply(long base, long exponent, long modulus) {
        long result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the base: ");
        long base = scanner.nextLong();

        System.out.print("Enter the exponent: ");
        long exponent = scanner.nextLong();

        System.out.print("Enter the modulus: ");
        long modulus = scanner.nextLong();

        long result = squareMultiply(base, exponent, modulus);

        System.out.println("Result: " + result);

        scanner.close();
    }
}
