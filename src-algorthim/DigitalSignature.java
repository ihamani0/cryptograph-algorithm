import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;

public class DigitalSignature {

    private static String PathFile = "";

    public static void main(String[] args) throws Exception {

        String message = "Hamani issam";

        // Generate a key pair
        KeyPair keyPair = generateKeyPair();

        // Generate the digital signature using MessageDigest and Cipher classes
        byte[] signature1 = SingeMessageCipher(message, keyPair.getPrivate());

        // Generate the digital signature using Signature class
        byte[] signature2 = SignMessageWithClassSign(message, keyPair.getPrivate());

        // Verify the signature using MessageDigest and Cipher classes
        boolean isVerified1 = verifySignatureWithMessageDigestAndCipher(message, signature1, keyPair.getPublic());

        // Verify the signature using Signature class
        boolean isVerified2 = verifySignatureWithSignatureClass(message, signature2, keyPair.getPublic());

        // Compare the two signatures
        if (isVerified1 && isVerified2) {
            System.out.println("Both signatures are valid!");
        } else {
            System.out.println("One or both signatures are invalid!");
        }
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] SingeMessageCipher(String message, PrivateKey privateKey)
            throws Exception {

        // Hash the message
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = messageDigest.digest(message.getBytes());

        // Encrypt the message hash with the private key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(messageHash); // Encrypte the Hash message
    }

    public static byte[] SignMessageWithClassSign(String message, PrivateKey privateKey) throws Exception {

        // second method
        // give the instance hash fun and method encrypt rsa
        Signature signature = Signature.getInstance("SHA256withRSA");
        // givr the private key to sing later
        signature.initSign(privateKey);
        // hash the message
        signature.update(message.getBytes());
        return signature.sign(); // and return the sign message
    }

    public static boolean verifySignatureWithMessageDigestAndCipher(String message, byte[] signature,
            PublicKey publicKey) throws Exception {
        // Hash the message
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = messageDigest.digest(message.getBytes());

        // Decrypt the signature with the public key
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedSignature = cipher.doFinal(signature);

        // Compare the decrypted signature with the message hash
        return Arrays.equals(decryptedSignature, messageHash);
    }

    public static boolean verifySignatureWithSignatureClass(String message, byte[] signature, PublicKey publicKey)
            throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(publicKey);
        sign.update(message.getBytes());
        return sign.verify(signature);
    }

    public static void writeInFile(String Path, Signature signature) throws SignatureException, IOException {
        byte[] signatureBytes = signature.sign();
        FileOutputStream fos = new FileOutputStream(Path);
        fos.write(signatureBytes);
        fos.close();
    }

}
