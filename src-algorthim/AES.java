import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AES {

    private static final int KEY_SIZE = 128;
    private static SecretKey Key;

    // private static String filePath =
    // "C:\\Users\\issam\\OneDrive\\Desktop\\aes.txt";
    private static String filePath = "/home/hmn/Desktop/aes.txt";
    private static String filePathEncrypte = "/home/hmn/Desktop/aesEnc.txt";
    private static String filePathDecrypte = "/home/hmn/Desktop/aesDec.txt";
    private static String filePathKey = "/home/hmn/Desktop/key.txt";

    public static void main(String[] args) throws IOException {

        try {
            GenerateKey();

            // for encrypte
            encrypt();
            // for encrypte
            decrypte();

        } catch (Exception e) {
            // TODO: handle exception
        }

        // for (byte x: addPadding(FileBytes,
        // getPaddingBytesRequired(FileBytes.length))) {
        // System.out.println(x);
        // }
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

    // Encrypt

    private static void encrypt() throws Exception {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKeygenerate());

        byte[] dataFile = readFile(filePath); // read file and return the data in type byte

        byte[] addPaddInDataFile = addPadding(dataFile, getPaddingBytesRequired(dataFile.length)); // add paddin in
                                                                                                   // dataFile

        byte[] encryptedData = cipher.doFinal(addPaddInDataFile);// make encrypt to the dataFile with method doFinal and
                                                                 // flah EncypteMod

        WriteInFile(encryptedData, filePathEncrypte); // make data from byte to Ascii aplhabitc and return it
    }

    private static void decrypte() throws Exception {

        Cipher Decryptecipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        Decryptecipher.init(Cipher.DECRYPT_MODE, getKeygenerate());

        byte[] dataFile = readFile(filePathEncrypte);

        byte[] DencryptedData = Decryptecipher.doFinal(dataFile);

        WriteInFile(DencryptedData, filePathDecrypte);
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

    private static String encode(byte[] encypteData) {
        return Base64.getEncoder().encodeToString(encypteData);
    }

    private static void WriteInFile(byte[] Data, String FilePath) throws IOException {
        try {

            FileOutputStream fos = new FileOutputStream(FilePath);
            fos.write(Data);
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

}
