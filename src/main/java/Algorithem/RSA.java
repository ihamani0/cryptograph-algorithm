package Algorithem;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {

    public static int KeySize ;

    public static String encrypt(String message, String PublicKey) throws Exception {
        // Encrypt message using public key

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(PublicKey));
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedMessage;

    }

    public static String decrypte(String encryptedBytes , String PrivatKey) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        // Decrypt message using private key
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(PrivatKey));

        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBytes));
        String decryptedMessage = new String(decryptedBytes, "UTF-8");
        return decryptedMessage;
    }


    private static PublicKey getPublicKey(String publicKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Adjust algorithm as needed
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey getPrivateKey(String privateKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA"); // Adjust algorithm as needed
        return keyFactory.generatePrivate(keySpec);
    }

    public static KeyPair generateRSAKeyPair() {
        try {
            // Initialize the KeyPairGenerator with RSA algorithm
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

            // Set the key size (adjust as needed)
            keyPairGenerator.initialize(KeySize);

            // Generate the key pair
            return keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
