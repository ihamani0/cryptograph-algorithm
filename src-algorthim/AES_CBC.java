import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
/*cipher block chaining
the Public-Key Cryptography Standards = PKCS5 padding



*/ 

public class AES_CBC {
    private final static String ALGO = "AES/CBC/PKCS5Padding";
    //specifying the algorithm and mode to be used for encryption and decryption.

    public static void main(String[] args)
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
         //create an instance of generated key to generate a secret key 128 bits 
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        // byte array to the initialization vector , is a random value 
       // to ensure different cipher blocks for the same plaintext.
        byte[] ivBytes = new byte[16];
        SecureRandom random = new SecureRandom(); // securerandom to generate the iv
        random.nextBytes(ivBytes);
        // ivpar is object created by using iv bate array 
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
         // create instnc of cipher using algo 
        Cipher cipher = Cipher.getInstance(ALGO);
        
        // *** BAAAANG HERE WE HAVE THE Encryption  ** // 

        // The cipher is initialized fr encry mode using init with iv and key
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
          // hello encrypted with dofinal mthd and stored in out.. array 
        byte[] outputEncryption = cipher.doFinal("hello".getBytes("UTF-8")); // hello nconvertiwha lel byte b utf -8
        // mn byte to string and printed out.using base 64
        String output = Base64.getEncoder().encodeToString(outputEncryption);

        System.out.println(output);

       // *** BAAAANG HERE WE HAVE THE decryption  ** // 

       //The cipher is re-initialized for decryption mode 
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] outputDecrypted = cipher.doFinal(Base64.getDecoder().decode(output));
        // to string 
        System.out.println(new String(outputDecrypted, "UTF-8"));
    }
}
/*
 * base 64 hia lms2oula 3la ta7wil byte to string o l3ks  * 









 
 */