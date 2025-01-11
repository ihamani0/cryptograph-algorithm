import java.security.SecureRandom;

public class MillerRabinInt {

    private static final int CERTAINTY = 10;

    public static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n < 2 || n % 2 == 0) {
            return false;
        }

        int d = n - 1;
        int r = 0;
        while (d % 2 == 0) {
            d /= 2;
            r++;
        }

        SecureRandom random = new SecureRandom();

        outerLoop:
        for (int i = 0; i < CERTAINTY; i++) {
            int a = getRandomA(n);
            int x = modPow(a, d, n);
            if (x != 1 && x != n - 1) {
                for (int j = 1; j < r; j++) {
                    x = modPow(x, 2, n);
                    if (x == 1) {
                        return false; // n is composite
                    }
                    if (x == n - 1) {
                        continue outerLoop;
                    }
                }
                return false; // n is composite
            }
        }
        return true; // n is likely prime
    }

    private static int modPow(int base, int exponent, int modulus) {
        int result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            base = (base * base) % modulus;
            exponent /= 2;
        }
        return result;
    }

    private static int getRandomA(int n) {
        SecureRandom random = new SecureRandom();
        int result;
        do {
            result = random.nextInt(n - 3) + 2; // Random number in the range [2, n-2]
        } while (result < 2 || result >= n - 1);
        return result;
    }

    public static void main(String[] args) {
        int testNumber = 123456789;

        if (isPrime(testNumber)) {
            System.out.println(testNumber + " is likely prime.");
        } else {
            System.out.println(testNumber + " is composite.");
        }
    }
}
