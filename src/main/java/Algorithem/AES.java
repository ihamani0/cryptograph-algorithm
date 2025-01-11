package Algorithem;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AES  {
    private static final int KEY_SIZE = 128;

    public static SecretKey key;
    public static byte[] IV;

    // method generate key AES-128
    private static SecretKey GenerateKey() throws NoSuchAlgorithmException {

            KeyGenerator Kgenerator = KeyGenerator.getInstance("AES");
            Kgenerator.init(KEY_SIZE);// 128 -bit
            SecretKey keygen = Kgenerator.generateKey(); // genrate key

            // return new SecretKeySpec(key.getEncoded(), "AES"); //
            // javax.crypto.spec.SecretKeySpec@fffe8c3b
        key=keygen;
        return keygen;
    }

    public static String encrypt(String PlainText) throws Exception {


        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, GenerateKey());

        byte[] encryptedBytes = cipher.doFinal(PlainText.getBytes());

        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }


    public static String decrypte(String CipherText , SecretKey keyFile) throws Exception {

        Cipher Decryptecipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Decryptecipher.init(Cipher.DECRYPT_MODE, keyFile);

        byte[] dataFile =  Base64.getDecoder().decode(CipherText);


        byte[] decryptedBytes = Decryptecipher.doFinal(dataFile);

        String decryptedText = new String(decryptedBytes);
        return  decryptedText ;
    }



    //Mod Cbc

    public static String encryptCbc(String PlainText) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Generate a random initialization vector (IV)
        byte[] iv = new byte[cipher.getBlockSize()];
        new SecureRandom().nextBytes(iv);
        AES.IV=iv;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, GenerateKey(), ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(PlainText.getBytes());// make encrypt to the dataFile with method doFinal and

        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        return encryptedText;
    }

    public static String decrypteCbc(String CipherText , SecretKey keyFile , byte[] IVvec) throws Exception {

            Cipher Decryptecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = new byte[Decryptecipher.getBlockSize()];
            iv = IVvec;

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            Decryptecipher.init(Cipher.DECRYPT_MODE, keyFile, ivParameterSpec);

            byte[] dataFile =  Base64.getDecoder().decode(CipherText);


            byte[] decryptedBytes = Decryptecipher.doFinal(dataFile);

            String decryptedText = new String(decryptedBytes);
            return  decryptedText ;


    }


}
