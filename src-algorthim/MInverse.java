public class MInverse {
    

        public static void main(String[] args) {
            int a = 35;
            int b = 11;
            int MInverse = Minverse(a, b);
            System.out.println(" A and B is : "+ MInverse);
        }
    
        public static int Minverse(int a, int b) {
            
            int t1 = 0 , t2 = 1 , T ;
            while (b != 0) {
                int div = a / b;//5 -
                int rest = a % b;//2 - 
    
                

                T = t1 - t2 * div ;

                a = b; // 51 
                b = rest; // 2 

                t1 = t2 ; 
                t2 = T;

    
            }
    
            return t1;
        }
    
    
}
