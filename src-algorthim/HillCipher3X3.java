import java.security.KeyManagementException;
import java.util.Scanner;

public class HillCipher3X3 {

    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String Key = "MBLEMBLEM";

        int[][] keyMatrix = new int[3][3];
        keyMatrix = getKeyMatrix(Key);

        

        // Get the plaintext from the user
        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine();

        String cipher = Encrypt(plaintext, keyMatrix);
        System.out.println("Encrypt Is " + cipher);

        String newPlainText = Dencrypt(cipher, keyMatrix);

        if (newPlainText.length() != plaintext.length()) {
            System.out.println("PlainText After Deycrpte : " + removePadding(plaintext, newPlainText));
        } else {
            System.out.println("PlainText After Deycrpte : " + newPlainText);
        }

    }

    private static int[][] getKeyMatrix(String key) {
        // Convert the key string to a 2x2 matrix
        int[][] keyMatrix = new int[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = HillCipher3X3.alphabet.indexOf(key.charAt(k));
                keyMatrix[i][j] = index;
                k++;
            }
        }
        return keyMatrix;
    }

    public static String Encrypt(String plaintext, int[][] keyMatrix) {
        StringBuilder cipherText = new StringBuilder();

        while (plaintext.length() % 3 != 0)
            plaintext += "0";

        for (int i = 0; i < plaintext.length(); i += 3) {
            String pair = plaintext.substring(i, Math.min(i + 3, plaintext.length()));

            // make this two char pair in when vectore
            int[] vector = new int[3];
            for (int j = 0; j < 3; j++) {
                int index = alphabet.indexOf(pair.charAt(j));
                vector[j] = index;
            }

            int[] encryptedVector = multiplyMatrix(vector, keyMatrix);

            String encryptedPair = "";
            for (int j = 0; j < 3; j++) {
                int encryptedIndex = (encryptedVector[j] + 26) % 26;
                char encryptedChar = alphabet.charAt(encryptedIndex);
                encryptedPair += encryptedChar;
            }

            cipherText.append(encryptedPair);
        }

        return cipherText.toString();
    }

    public static String Dencrypt(String Ciphertext, int[][] keyMatrix) {
        StringBuilder PlainText = new StringBuilder();

        while (Ciphertext.length() % 3 != 0)
            Ciphertext += "0";

        int[][] inverseKeyMatrix = GetInvKeyMatrix(keyMatrix);

        // inverseKeyMatrix = etInvDet(CalculatesDet(keyMatrix))

        for (int i = 0; i < Ciphertext.length(); i += 3) {
            String pair = Ciphertext.substring(i, Math.min(i + 3, Ciphertext.length()));

            // make this two char pair in when vectore
            int[] vector = new int[3];
            for (int j = 0; j < 3; j++) {
                int index = alphabet.indexOf(pair.charAt(j));
                vector[j] = index;
            }

            int[] PlainVector = multiplyMatrix(vector, inverseKeyMatrix);

            String PlainPair = "";
            for (int j = 0; j < 3; j++) {
                int PlainIndex = (PlainVector[j] + 26) % 26;
                char PlainChar = alphabet.charAt(PlainIndex);
                PlainPair += PlainChar;
            }

            PlainText.append(PlainPair);
        }
        return PlainText.toString();
    }

    // Multiplication Matrix
    private static int[] multiplyMatrix(int[] vector, int[][] matrix) {
        int[] product = new int[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                sum += vector[j] * matrix[i][j];
            }
            product[i] = sum;
        }

        return product;
    }

    /*
     * calcilate detrmin
     */
    private static int CalculatesDet(int[][] keyMatrix) {

        int a = keyMatrix[0][0];
        int b = keyMatrix[0][1];
        int c = keyMatrix[0][2];

        // int d ,e , f , g ,h,i ;

        // d = keyMatrix[1][0];
        // e = keyMatrix[1][1];
        // f = keyMatrix[1][2];

        // g = keyMatrix[2][0];
        // h = keyMatrix[2][1];
        // i = keyMatrix[2][2];
        // System.out.println(a+" , "+b+" , "+c);
        // System.out.println(d+" , "+e+" , "+f);
        // System.out.println(g+" , "+h+" , "+i);

        int col1 = a * ((keyMatrix[1][1] * keyMatrix[2][2]) - (keyMatrix[1][2] * keyMatrix[2][1]));
        int col2 = b * ((keyMatrix[1][0] * keyMatrix[2][2]) - (keyMatrix[1][2] * keyMatrix[2][0]));
        int col3 = c * ((keyMatrix[1][0] * keyMatrix[2][1]) - (keyMatrix[1][1] * keyMatrix[2][0]));

        int det = col1 - col2 + col3;

        while (det < 0) {
            det += 26;
        }

        return (det % 26);

    }

    /*
     * Test if matrix inversibel
     */
    private static boolean IsInversible(int det) {
        return det != 0;
    }

    /*
     * Finde the invers of detrminate
     */

    private static int GetInvDet(int det) {

        det %= 26;

        for (int i = 0; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                return i;
            }
        }
        return -1;

    }

    private static int[][] GetmatrixAdj(int[][] keyMatrix) {

        int a = keyMatrix[0][0];
        int b = keyMatrix[0][1];
        int c = keyMatrix[0][2];

        int d = keyMatrix[1][0];
        int e = keyMatrix[1][1];
        int f = keyMatrix[1][2];

        int g = keyMatrix[2][0];
        int h = keyMatrix[2][1];
        int i = keyMatrix[2][2];

        int[][] Adj = new int[3][3];

        Adj[0][0] = ((e * i) - (f * h)) % 26;
        Adj[0][1] = -(((d * i) - (f * g)) % 26);
        Adj[0][2] = ((d * h) - (e * g)) % 26;

        Adj[1][0] = -(((b * i) - (c * h)) % 26);
        Adj[1][1] = ((a * i) - (c * g)) % 26;
        Adj[1][2] = -(((a * h) - (b * g)) % 26);

        Adj[2][0] = ((b * f) - (c * e)) % 26;
        Adj[2][1] = -(((a * f) - (c * d)) % 26);
        Adj[2][2] = ((a * e) - (b * d)) % 26;

        return Adj;
    }

    private static int[][] GetInvKeyMatrix(int[][] keyMatrix) {

        int invDet = GetInvDet(CalculatesDet(keyMatrix));

        int[][] Adj = GetmatrixAdj(keyMatrix);

        int[][] inversKeyMatrix = new int[3][3];

        inversKeyMatrix[0][0] = (Adj[0][0] * invDet) % 26;
        inversKeyMatrix[0][1] = (Adj[1][0] * invDet) % 26;
        inversKeyMatrix[0][2] = (Adj[2][0] * invDet) % 26;

        inversKeyMatrix[1][0] = (Adj[0][1] * invDet) % 26;
        inversKeyMatrix[1][1] = (Adj[1][1] * invDet) % 26;
        inversKeyMatrix[1][2] = (Adj[2][1] * invDet) % 26;

        inversKeyMatrix[2][0] = (Adj[0][2] * invDet) % 26;
        inversKeyMatrix[2][1] = (Adj[1][2] * invDet) % 26;
        inversKeyMatrix[2][2] = (Adj[2][2] * invDet) % 26;

        return MakeMatrixPos(inversKeyMatrix);
    }

    private static int[][] MakeMatrixPos(int[][] InvkeyMatrix) {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (InvkeyMatrix[i][j] < 0) {
                    InvkeyMatrix[i][j] += 26;
                }

            }

        }
        return InvkeyMatrix;
    }

    private static String removePadding(String plaintext, String newPlainText) {

        return newPlainText.substring(0, plaintext.length());
    }

}
