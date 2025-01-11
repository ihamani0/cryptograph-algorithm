package Algorithem;

public class ViginerCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static String  Encrypte(String plainText , String key ){
        StringBuilder CipherText = new StringBuilder();
       String newKey = getNewKey(plainText,key);



        for (int i = 0 ; i < plainText.length()  ; i++){
            char char_p = plainText.charAt(i); // get one char from string plainText
            char char_k = newKey.charAt(i); // get one char from newKey


            //get postion of char_p and char_k
            int pos_p = alphabet.indexOf(char_p);
            int pos_k = alphabet.indexOf(char_k);

            //Encrypte
            int pos_c = ((pos_p + pos_k) % 26) ;

            //get new char with postion cipher
            char char_c = alphabet.charAt(pos_c);

            CipherText.append(char_c);
        }



        return CipherText.toString();
    }


    public static String  Dencrypte(String cipherText , String key ){
        StringBuilder PlainText = new StringBuilder();
        String newKey = getNewKey(cipherText,key);

        for (int i = 0 ; i < cipherText.length()  ; i++){
            char char_p = cipherText.charAt(i); // get one char from string plainText
            char char_k = newKey.charAt(i); // get one char from newKey


            //get postion of char_p and char_k
            int pos_p = alphabet.indexOf(char_p);
            int pos_k = alphabet.indexOf(char_k);

            //Encrypte
            int pos_c = ((pos_p - pos_k + 26) % 26) ;

            //get new char with postion cipher
            char char_c = alphabet.charAt(pos_c);

            PlainText.append(char_c);
        }



        return PlainText.toString();
    }
    protected static String getNewKey(String plainText , String Key){
        //i and j for loop
        int i , j ;
        StringBuilder newKey = new StringBuilder();

        //Make Plain Text and key in array
        char[] PlainText = plainText.toCharArray();
        char[] keyText = Key.toCharArray();
        //Make new Key depend for lenght plainText
        //char newKey[] = new char [PlainText.length];


        for (i = 0 , j= 0 ; i < PlainText.length ; i++ , j++){

            if(j==keyText.length){ j = 0;}//Test if j arrive for last char in key to start over again
            //newKey[i] = keyText[j] ;
            newKey.append(keyText[j]);
        }

        return newKey.toString();
    }
}
