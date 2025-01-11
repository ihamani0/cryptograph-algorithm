package Algorithem;

public class AffineCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    public static String  Encrypte(String plainText , String key1 , String key2) throws MyExcption {

        StringBuilder CipherText = new StringBuilder();
        int k1 = alphabet.indexOf(key1);
        int k2 = alphabet.indexOf(key2);

                if (!KeyPrime(k1)){
                    throw new MyExcption("The Key 1 That You Entre Is Not Valide !");
                }

        for (int i = 0 ; i < plainText.length() ; i++){
            //make each char in variable c
            char c = plainText.charAt(i);
            //get position for c char and put it in position
            int Position = alphabet.indexOf(c);

            //calculate position for new char 'crypto'
            int cryptoPosition = ((k1 * Position) + k2) % 26 ;
            //new position for crypto
            c =  alphabet.charAt(cryptoPosition);

            CipherText.append(c);

        }
        //System.out.println(cipher);
        return CipherText.toString();
    }

    public static String  Dencrypte(String cipherText , String key1 , String key2){
        StringBuilder PlainText = new StringBuilder();
        int k1 = alphabet.indexOf(key1);
        int k2 = alphabet.indexOf(key2);



        int invK1 = getInverseKey(k1);


        for (int i =0 ; i < cipherText.length();i++){
            char c = cipherText.charAt(i);

            int Postion = alphabet.indexOf(c);


            //calcule postion for new char 'crypto'
            int plainPostion = ((invK1 * ( Postion - k2)) % 26 );
            while(plainPostion < 0) {
                plainPostion+=26;
            }



            //new postion for crypto
            c =  alphabet.charAt(plainPostion);
            PlainText.append(c);
        }

        return PlainText.toString();
    }

    private static   int getInverseKey(int key) {
        //make sure that a under M
        key %= 26;
        for (int x = 1; x < 26; x++) {
            if ((key * x) % 26 == 1) {
                return x;
            }
        }
        return -1;
    }

    private static boolean KeyPrime(int k){
        if(GCD.FindGcd(k , 26) == 1){
            return true;
        }
        return false;
    }
}
