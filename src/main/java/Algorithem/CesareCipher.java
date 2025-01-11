package Algorithem;

import java.security.Key;

public class CesareCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String  encrypte(String plainText , String key ){
        StringBuilder CipherText = new StringBuilder();
        int k1 = alphabet.indexOf(key);


        for (int i = 0 ; i < plainText.length() ; i++){
            //make each char in variable c
            char c = plainText.charAt(i);
            //get postin for c char and put it in postion
            int Postion = alphabet.indexOf(c);

            //calcule postion for new char 'crypto'
            int cryptoPostion = ((k1 + Postion)  % 26 );
            //new postion for crypto

            c = alphabet.charAt(cryptoPostion);

            CipherText.append(c);

        }
        //System.out.println(cipher);
        return CipherText.toString();
    }


    public static  String Decrypte(String cipherText , String key){

        StringBuilder PlainText = new StringBuilder();
        int k1 = alphabet.indexOf(key);
        String Plain ="";
        //find inverse for key 1


        for (int i =0 ; i < cipherText.length();i++){
            char c = cipherText.charAt(i);

            int Postion = alphabet.indexOf(c);


            //calcule postion for new char 'crypto'
            int plainPostion =  (( Postion - k1) % 26) ;

            while(plainPostion < 0) {
                plainPostion += 26;
            }



            //new postion for crypto
            c =  alphabet.charAt(plainPostion);
            PlainText.append(c);
        }

        return  PlainText.toString();
    }






}
