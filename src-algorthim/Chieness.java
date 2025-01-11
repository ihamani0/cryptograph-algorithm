public class Chieness {

        public static void main(String[] args) {
            int[] num = {5,7,11} ;
            int[] rem= {2,3,2};
            findMinX(num, rem);
            
        }

        public static void  findMinX(int[] num, int[] rem) {

            for (int i = 0 ; i < num.length ; i++){
                for (int j = i+1 ; j < num.length ; j++){
                    if(Chieness.GCD(num[i],num[j]) != 1 ){
                        System.out.println("The Number are Not Co-Prime");
                    }
                }

            }

            int M = 1;
            for (int n : num) {
                M *= n;
            }
            System.out.println("M = " +M);
            int result = 0;
            for (int i = 0; i < num.length; i++) {
                int pp = M / num[i];
                System.out.println("The num = "+num[i] );
                System.out.println("The pp = "+pp );
                System.out.println("The invers = "+computeInverse(pp, num[i]));
                System.out.println("The result = "+rem[i] * pp * computeInverse(pp, num[i]) );
                System.out.println("_____________________");
                result += rem[i] * pp * computeInverse(pp, num[i]);
            }

            System.out.println("The X = "+result );
            System.out.println("The X = "+result % M);
        }



    public static int computeInverse(int a, int b) {
        int m = b, t, q;
        int x = 0, y = 1;

        if (b == 1) {
            return 0;
        }

        // Apply extended Euclid Algorithm.
        while (a > 1) {
            q = a / b;
            t = b;
            b = a % b;
            a = t;
            t = x;
            x = y - q * x;
            y = t;
        }

        // Make x1 positive.
        if (y < 0) {
            y += m;
        }

        return y;
    }

    public static int GCD(int a ,int b){
            if(b==0){
                return a;
            }
        return GCD(b , a % b);
    }

    }


