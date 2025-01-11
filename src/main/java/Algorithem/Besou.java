package Algorithem;

public class Besou {
    public static int[] FindConficent(int a , int b ){
        int u0 = 1, u1 = 0;
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



        }

        int[] conficent = { u0, v0 };
        return conficent;
    }
}
