import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash {

    private static String PathFileText = "/home/hmn/Text";
    private static String PathFileHash = "/home/hmn/Hash";

    private static String HashFromWebSite = "59D1DE700DF8265ACFD9ECB24C57D88F48346F2EEBCEFA25704F923AD9596854";

    public static void main(String[] args) throws IOException, FileNotFoundException, NoSuchAlgorithmException {

        String Text = readFile(PathFileText);

        byte[] degste = Hash(Text, "SHA-256");

        writeFile(PathFileHash, bytesToHex(degste));

        String ChangedText = changeCharacter(Text);

        byte[] Newdegste = Hash(ChangedText, "SHA-256");

        boolean equal = compareHashes(degste, Newdegste);

        double avalancheEffect = calculateAvalancheEffect(degste, Newdegste);

        System.out.println("Original text: " + Text);
        System.out.println("SHA-256 hash: " + bytesToHex(degste));
        System.out.println("Changed text: " + ChangedText);
        System.out.println("Changed SHA-256 hash: " + bytesToHex(Newdegste));
        System.out.println("Hashes are equal: " + equal);

        System.out.println("Avalanche effect: " + avalancheEffect + "%");

        System.out.println("Compare Same HashText her and WebSite "
                + compareHashesString(bytesToHex(degste).toUpperCase(), HashFromWebSite));

    }

    /*
     * Read in File using class scanner and return String
     */
    private static String readFile(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String text = scanner.nextLine();
        scanner.close();
        return text;
    }

    /*
     * write in File void
     */
    private static void writeFile(String filename, String data) throws IOException {
        FileWriter writer = new FileWriter(filename, StandardCharsets.UTF_8);
        writer.write(new String(data));
        writer.close();
    }

    private static byte[] Hash(String text, String instance) throws NoSuchAlgorithmException {
        MessageDigest hashD = MessageDigest.getInstance(instance);
        hashD.update(text.getBytes()); //
        return hashD.digest();
    }

    private static String changeCharacter(String text) {

        String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        int randomIndex = ((int) (Math.random() * validChars.length())) % 52;

        char randomChar = validChars.charAt(randomIndex);

        String changedText = randomChar + text.substring(1, text.length() - 2) + randomChar + randomChar;

        return changedText;
    }

    private static boolean compareHashes(byte[] hash1, byte[] hash2) {
        if (hash1.length != hash2.length) {
            return false;
        }
        for (int i = 0; i < hash1.length; i++) {
            if (hash1[i] != hash2[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareHashesString(String hash1, String hash2) {
        if (hash1.length() != hash2.length()) {
            return false;
        }
        for (int i = 0; i < hash1.length(); i++) {
            if (hash1.charAt(i) != hash2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static double calculateAvalancheEffect(byte[] hash1, byte[] hash2) {
        int numberOfFlippingBits = 0;

        for (int i = 0; i < hash1.length; i++) {
            if (hash1[i] != hash2[i]) {
                numberOfFlippingBits++;
            }
        }

        double avalancheEffect = (double) numberOfFlippingBits / (double) (hash1.length * 8) * 100;
        return avalancheEffect;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
