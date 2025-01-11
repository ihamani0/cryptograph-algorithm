import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import javax.crypto.NoSuchPaddingException;

public class RSA_P {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static void main(String[] args) throws Exception {

        RSA_P s1 = new RSA_P();
        s1.generateKey();
        String message = "helloworld";

        System.out.println("Original message: " + message);
        System.out.println("Encrypted message: " + s1.encrypt(message));
        System.out.println("Decrypted message: " + s1.decrypte(s1.encrypt(message)));

    }

    public void generateKey() throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512); // Key length in bits
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public String encrypt(String message) throws Exception {
        // Encrypt message using public key

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedMessage;
    }

    public String decrypte(String encryptedBytes) throws Exception {

        Cipher cipher = Cipher.getInstance("RSA");
        // Decrypt message using private key
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedBytes));
        String decryptedMessage = new String(decryptedBytes, "UTF-8");
        return decryptedMessage;
    }
}
