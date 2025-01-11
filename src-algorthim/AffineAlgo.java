public class AffineAlgo {



    protected  char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();



    //ax+b
    public String  encrypte(String plainText , int k1 , int k2){
            String cipher ="";
            for (int i = 0 ; i < plainText.length() ; i++){
                //make each char in variable c
                char c = plainText.charAt(i);
                //get postin for c char and put it in postion
                int Postion = this.getPosition(c);

                //calcule postion for new char 'crypto'
                int cryptoPostion = ((k1 * Postion) + k2) % 26 ;
                //new postion for crypto
                c =  this.getChar(cryptoPostion);

                cipher += c;

            }
            //System.out.println(cipher);
        return cipher;
    }

    public  void  dencrypte(String cipherText ,int k1 , int k2){
        String Plain ="";
        //find inverse for key 1
        int k1_inv = this.modInverse(k1);

        for (int i =0 ; i < cipherText.length();i++){
            char c = cipherText.charAt(i);

            int Postion = this.getPosition(c);


            //calcule postion for new char 'crypto'
            int plainPostion = ((k1_inv * ( Postion - k2)) % 26 );
            if(plainPostion < 0) {
                plainPostion = plainPostion + 26;
            }



            //new postion for crypto
            c =  this.getChar(plainPostion);
            Plain+=c;
        }


        System.out.println("Plain text :" +Plain);
    }



    private  int modInverse(int a) {
        //make sure that a under M
        a %= 26;
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) {
                return x;
            }
        }
        return -1;
    }

    private int getPosition(char alpha){
        for (int i = 0 ; i < this.alphabet.length ; i++){
            if (this.alphabet[i] == alpha) {
                return  i;
            }
        }
        return -1;
    }

    private char getChar(int Postion){
        for (int i = 0 ; i < this.alphabet.length ; i++){
            if (Postion == i) {
                return  this.alphabet[i];
            }
        }
        return 'n';
    }
}
