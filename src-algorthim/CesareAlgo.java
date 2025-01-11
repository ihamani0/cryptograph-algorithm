public class CesareAlgo {

    protected  char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public String  encrypte(String plainText , int k1 ){
        String cipher ="";
        for (int i = 0 ; i < plainText.length() ; i++){
            //make each char in variable c
            char c = plainText.charAt(i);
            //get postin for c char and put it in postion
            int Postion = this.getPosition(c);

            //calcule postion for new char 'crypto'
            int cryptoPostion = (k1 + Postion)  % 26 ;
            //new postion for crypto
            c =  this.getChar(cryptoPostion);

            cipher += c;

        }
        //System.out.println(cipher);
        return cipher;
    }


    public  void  dencrypte(String cipherText ,int k1 ){
        String Plain ="";
        //find inverse for key 1


        for (int i =0 ; i < cipherText.length();i++){
            char c = cipherText.charAt(i);

            int Postion = this.getPosition(c);


            //calcule postion for new char 'crypto'
            int plainPostion =  ( Postion - k1) % 26 ;
            if(plainPostion < 0) {
                plainPostion = plainPostion + 26;
            }



            //new postion for crypto
            c =  this.getChar(plainPostion);
            Plain+=c;
        }


        System.out.println("cesar plain text :"+Plain);
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
