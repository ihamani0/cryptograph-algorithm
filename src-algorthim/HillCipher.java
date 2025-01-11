import java.util.Scanner;

public class HillCipher {

    private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cipherText = new String();

        // Get the key matrix from the user
        
        System.out.println("Enter the key matrix");
        int[][] keyMatrix = getKeyMatrix(sc);

        //System.out.println(detInverse(10));
        // for (int i = 0; i < keyMatrix.length; i++) {

        //     for (int j = 0; j < keyMatrix[i].length; j++) {

        //         System.out.println(keyMatrix[i][j]);
        //     }

        // }

        // int[][] inv = invertMatrix(keyMatrix);

        // for (int i = 0; i < inv.length; i++) {

        //     for (int j = 0; j < inv[i].length; j++) {

        //         System.out.println(inv[i][j]);
        //     }

        // }

        // Get the plaintext from the user

        System.out.println("Enter the plaintext:");
        String plaintext = sc.nextLine();

        // Encrypt the plaintext
        cipherText = Encrypt(plaintext, keyMatrix);
        System.out.println("Ciphertext: " + cipherText);

        System.out.println("Plaion Text aafter encryption : " + decrypt(cipherText,
        keyMatrix));

    }

    // Method to get the key matrix from the user
    private static int[][] getKeyMatrix(Scanner sc) {
        String key = sc.nextLine();

        // Convert the key string to a 2x2 matrix
        int[][] keyMatrix = new int[2][2];
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int index = HillCipher.alphabet.indexOf(key.charAt(k));
                keyMatrix[i][j] = index;
                k++;
            }
        }

        return keyMatrix;
    }

    public static String Encrypt(String plaintext, int[][] keyMatrix) {
        StringBuilder cipherText = new StringBuilder();

        // add padding
        while (plaintext.length() % 2 != 0) {
            plaintext += "0";
        }

        // make the each cha pair and math.min(i=6 , lenght=5) to ensure when i it be 6
        // we take number of lenght plain text and sub string it
        for (int i = 0; i < plaintext.length(); i += 2) {
            String pair = plaintext.substring(i, Math.min(i + 2, plaintext.length()));

            // make this two char pair in when vectore
            int[] vector = new int[2];
            for (int j = 0; j < 2; j++) {
                int index = alphabet.indexOf(pair.charAt(j));
                vector[j] = index;
            }

            int[] encryptedVector = multiplyMatrix(vector, keyMatrix);

            String encryptedPair = "";
            for (int j = 0; j < 2; j++) {
                int encryptedIndex = (encryptedVector[j] + 26) % 26;
                char encryptedChar = alphabet.charAt(encryptedIndex);
                encryptedPair += encryptedChar;
            }

            cipherText.append(encryptedPair);
        }
        // end loop

        return cipherText.toString();
    }

    // decryptio
    private static String decrypt(String ciphertext, int[][] keyMatrix) {
        StringBuilder decryptedTextBuilder = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            String pair = ciphertext.substring(i, Math.min(i + 2, ciphertext.length()));

            int[] encryptedVector = new int[2];
            for (int j = 0; j < 2; j++) {
                int index = alphabet.indexOf(pair.charAt(j));
                encryptedVector[j] = index;
            }

            int[][] inverseKeyMatrix = invertMatrix(keyMatrix);
            int[] decryptedVector = multiplyMatrix(encryptedVector, inverseKeyMatrix);

            String decryptedPair = "";
            for (int j = 0; j < 2; j++) {
                int decryptedIndex = (decryptedVector[j] + 26) % 26;
                char decryptedChar = alphabet.charAt(decryptedIndex);
                decryptedPair += decryptedChar;
            }

            decryptedTextBuilder.append(decryptedPair);
        }

        return decryptedTextBuilder.toString();
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

    // invers Matrix
    private static int[][] invertMatrix(int[][] keyMatrix) {

        int det = determinant(keyMatrix);
        int invDet = detInverse(det);
        // check if matrix inversible or not
        if (det == 0 || PGCD.GCD(det, 26) != 1 ) {
            throw new RuntimeException("Matrix is not invertible");
        }

        int[][] inversMatrix = new int[2][2];
        inversMatrix[0][0] = (((keyMatrix[1][1]) * invDet) % 26);
        inversMatrix[0][1] = (((-keyMatrix[0][1]) * invDet) % 26) + 26;
        inversMatrix[1][0] = (((-keyMatrix[1][0]) * invDet) % 26) + 26;
        inversMatrix[1][1] = ((keyMatrix[0][0]) * invDet) % 26;

        return inversMatrix;

    }

    public static int determinant(int[][] keyMatrix) {

        int det = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26;

        while (det < 0) {
            det += 26;
        }

        return det;
    }

    // Calculates the modular inverse of a number
    private static int detInverse(int a) {
        return bezout.Calcule(a, 26);
    }
}
