public class PGCD {

    public static int[] pgcd(int a , int b) {

        int[] result = new int[3];

        if (b == 0) {
            result[0] = a;
            result[1] = 1;
            result[2] = 0;
            return result;
        } else {
            int[] previousResult = pgcd(b, a % b);

            result[0] = previousResult[0];
            result[1] = previousResult[2];
            result[2] = previousResult[1] - (a / b) * previousResult[2];

            return result;
        }
    }

    public static int pgcdBezout(int a, int b) {
        int  u0 = 1, u1 = 0, v0 = 0, v1 = 1;
        while (b != 0) {
            int div = a / b;
            int rest = a % b;
            int u2 = u0 - rest * u1;
            int v2 = v0 - rest * v1;
            a = b; b = rest;
            u0 = u1; u1 = u2;
            v0 = v1; v1 = v2;
        }

        return u0;
    }
    public static int GCD(int a ,int b){
        if(b==0){
            return a;
        }
    return GCD(b , a % b);
    }
}
