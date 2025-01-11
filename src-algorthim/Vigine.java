public class Vigine {

    protected  char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public void Encrypte(String plainText , String Key){
        //ensure that plain text and key are lowercase
        plainText = plainText.toLowerCase();
        Key = Key.toLowerCase();

        String cipher = "";
        char newKey[] = Vigine.generateNewKey(plainText , Key );

        for (int i = 0 ; i < plainText.length()  ; i++){
            char char_p = plainText.charAt(i); // get one char from string plainText
            char char_k = newKey[i]; // get one char from newKey


            //get postion of char_p and char_k
                int pos_p = this.getPosition(char_p);
                int pos_k = this.getPosition(char_k);

            //Encrypte
                int pos_c = ((pos_p + pos_k) % 26) ;

            //get new char with postion cipher
                char char_c = this.getChar(pos_c);

            cipher += char_c ;


        }
        System.out.println("Cipher Text of Algorithm viginer : " +cipher);
        this.Decryption(cipher,newKey);

        /*System.out.println("Lenght of Plain Text" + plainText.length());
        for (char k : newKey) {
            System.out.print(k+"-");
        }*/
    }

    public void Decryption(String cipherText , char[] Key){
        String PlainText = "";

        for (int i = 0 ; i < cipherText.length()  ; i++){
            char char_c = cipherText.charAt(i); // get one char from string plainText
            char char_k = Key[i]; // get one char from newKey
            //get postion of char_p and char_k
            int pos_c = this.getPosition(char_c);
            int pos_k = this.getPosition(char_k);

            //Encrypte
            int pos_p = ((pos_c - pos_k + 26) % 26) ;
            //if(pos_p < 0){  pos_p = pos_p + 26 ;}

            //get new char with postion cipher
            char char_p = this.getChar(pos_p);

            PlainText += char_p ;


        }
        System.out.println("Plain Text of Algorithm viginer for Cipher Text :"+ cipherText  +" is: " +PlainText);
    }

    protected static char[] generateNewKey(String plainText , String Key){
        //i and j for loop
        int i , j ;

        //Make Plain Text and key in array
        char[] PlainText = plainText.toCharArray();
        char[] keyText = Key.toCharArray();
        //Make new Key depend for lenght plainText
        char newKey[] = new char [PlainText.length];


        for (i = 0 , j= 0 ; i < PlainText.length ; i++ , j++){
            if(j==keyText.length){ j = 0;}//Test if j arrive for last char in key to start over again
            newKey[i] = keyText[j] ; // make index  newkey take the value of  index of keyText
        }

        return newKey;
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
