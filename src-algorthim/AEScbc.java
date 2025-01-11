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

public class AEScbc {
    private static final int KEY_SIZE = 128;
    private static byte[] IV;

    private static String filePath = "/home/hmn/Desktop/aes.txt";
    private static String filePathEncrypte = "/home/hmn/Desktop/aesEnc.txt";
    private static String filePathDecrypte = "/home/hmn/Desktop/aesDec.txt";
    private static String filePathKey = "/home/hmn/Desktop/key.txt";
    private static final String FILE_PATH_IV = "/home/hmn/Desktop/iv.txt";

    public static void main(String[] args) throws IOException {

        try {
            GenerateKey();

            // // for encrypte
            encrypt();

            System.out.println(getIV());
            // // for encrypte
            decrypte();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    // method generate key AES-128
    private static void GenerateKey() throws NoSuchAlgorithmException {

        try {
            KeyGenerator Kgenerator = KeyGenerator.getInstance("AES");
            Kgenerator.init(KEY_SIZE);// 128 -bit
            SecretKey keygen = Kgenerator.generateKey(); // genrate key

            // return new SecretKeySpec(key.getEncoded(), "AES"); //
            // javax.crypto.spec.SecretKeySpec@fffe8c3b
            SaveKeygenerate(keygen);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private static void encrypt() throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        byte[] dataFile = readFile(filePath); // read file and return the data in type byte

        byte[] addPaddInDataFile = addPadding(dataFile, getPaddingBytesRequired(dataFile.length)); // add paddin in
                                                                                                   // dataFile
        // Generate a random initialization vector (IV)
        byte[] iv = new byte[cipher.getBlockSize()];
        new SecureRandom().nextBytes(iv);
        setIv(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, getKeygenerate(), ivParameterSpec);

        byte[] encryptedData = cipher.doFinal(addPaddInDataFile);// make encrypt to the dataFile with method doFinal and
                                                                 // flah EncypteMod
        System.out.println(iv);
        WriteInFile(encode(encryptedData), filePathEncrypte); // make data from byte to Ascii aplhabitc and return it
        // WriteInFile(encode(iv), FILE_PATH_IV);
    }

    private static void decrypte() throws Exception {

        try {
            String EncryptedData = new String(Files.readAllBytes(Paths.get(filePathEncrypte)));

            // String ivFile = new String(Files.readAllBytes(Paths.get(FILE_PATH_IV)));

            Cipher Decryptecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = new byte[Decryptecipher.getBlockSize()];
            iv = getIV();
            byte[] dataFile = decode(EncryptedData);

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            Decryptecipher.init(Cipher.DECRYPT_MODE, getKeygenerate(), ivParameterSpec);
            byte[] decryptedData = Decryptecipher.doFinal(dataFile);

            System.out.println(new String(decryptedData, "UTF-8"));

            FileWriter writer = new FileWriter(filePathDecrypte, StandardCharsets.UTF_8);
            writer.write(new String(decryptedData));
            writer.close();
        } catch (Exception e) {
            System.err.println("EX+> " + e);
        }
    }

    // Read file reture byte of content file
    private static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    // get rquired of the data giv lenght od data in file and return the padding
    // that we need to add to the block
    private static int getPaddingBytesRequired(int length) {
        return 16 - (length % 16);
    }

    // add the required padding that we need in the block
    private static byte[] addPadding(byte[] data, int paddingBytesRequired) {
        byte[] paddedData = new byte[data.length + paddingBytesRequired];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        for (int i = data.length; i < paddedData.length; i++) {
            paddedData[i] = (byte) 0;
        }
        return paddedData;
    }

    // from her start the algorithme from writin file

    // byte to string
    private static String encode(byte[] encypteData) {
        return Base64.getEncoder().encodeToString(encypteData);
    }

    // string to byte
    private static byte[] decode(String Data) {
        return Base64.getDecoder().decode(Data);
    }

    private static void WriteInFile(String Data, String FilePath) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(FilePath);
            fos.write(Data.getBytes());
            fos.close();

        } catch (Exception e) {
            System.err.println("Excption " + e);
        }
    }

    private static void SaveKeygenerate(SecretKey keygen) throws IOException {
        byte[] keyBytes = keygen.getEncoded();
        FileOutputStream fos = new FileOutputStream(filePathKey);
        fos.write(keyBytes);
        fos.close();
    }

    private static SecretKey getKeygenerate() throws IOException {

        FileInputStream fis = new FileInputStream(filePathKey);
        byte[] keyBytes = fis.readAllBytes();
        fis.close();
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        return secretKey;
    }

    private static void setIv(byte[] iv) {
        IV = iv;
    }

    // Getter method to retrieve the value from the property
    private static byte[] getIV() {
        return IV;
    }
}
