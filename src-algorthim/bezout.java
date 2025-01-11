/**
 * bezout
 */
public class bezout {

    public static void main(String[] args) {
        int a = 35;
        int b = 11;
        //int[] bezout = Calcule(a, b);
        // System.out.println(" value u is : " + bezout[0]);
        // System.out.println(" value v is : " + bezout[1]);

    }

    public static int Calcule(int a, int b) {
        int u0 = 1, u1 = 0; int m = b;
        int v0 = 0, v1 = 1;

        while (b != 0) {
            // calcule div a on b
            int div = a / b;
            int rest = a % b;

            // change value
            a = b;
            b = rest;

            int newU = u0 - div * u1;
            int newV = v0 - div * v1;

            u0 = u1;
            u1 = newU;
            v0 = v1;
            v1 = newV;

            /**
             * int q = a / b;
             * int temp = a;
             * a = b;
             * b = temp % b;
             * 
             * int newX = x0 - q * x1;
             * int newY = y0 - q * y1;
             * 
             * x0 = x1;
             * y0 = y1;
             * x1 = newX;
             * y1 = newY;
             */

        }
        while(u0<0){
            u0+=m;
        }
        return u0;
    }
}