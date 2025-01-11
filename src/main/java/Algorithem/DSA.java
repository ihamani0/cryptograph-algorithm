package Algorithem;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.Base64;

public class DSA {


    public static String SingeMessageCipher(String message , String algorithme)
            throws Exception {

        KeyPair keyPair = generateRSAKeyPair();
        // Hash the message
        MessageDigest messageDigest = MessageDigest.getInstance(algorithme);
        byte[] messageHash = messageDigest.digest(message.getBytes());

        // Encrypt the message hash with the private key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] encryptedBytes =  cipher.doFinal(messageHash); // Encrypte the Hash message

        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedMessage;
    }

    public static KeyPair generateRSAKeyPair() {
        try {
            // Initialize the KeyPairGenerator with RSA algorithm
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Set the key size (adjust as needed)
            keyPairGenerator.initialize(1024);

            // Generate the key pair
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
