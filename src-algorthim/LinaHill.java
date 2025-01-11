import java.util.Scanner;

public class LinaHill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the key matrix (3x3 for a basic example)
        int[][] keyMatrix = {
                {5, 24, 1},
                {13, 10, 6},
                {19, 5, 15}
        };

        System.out.println("Enter the plaintext (in uppercase):");
        String plaintext = scanner.nextLine();

        // Ensure the plaintext length is a multiple of the key matrix size
        int keySize = keyMatrix.length;
        int textLength = plaintext.length();
        int padding = keySize - (textLength % keySize);
        if (padding != keySize) {
            for (int i = 0; i < padding; i++) {
                plaintext += "X"; // Add 'X' to pad the plaintext
            }
        }

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, keyMatrix);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, keyMatrix);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    // Encrypt a block of text using the Hill cipher
    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int keySize = keyMatrix.length;
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += keySize) {
            String block = plaintext.substring(i, i + keySize);
            for (int j = 0; j < keySize; j++) {
                int sum = 0;
                for (int k = 0; k < keySize; k++) {
                    sum += (block.charAt(k) - 'A') * keyMatrix[k][j];
                }
                int encryptedChar = (sum % 26) + 'A';
                ciphertext.append((char) encryptedChar);
            }
        }

        return ciphertext.toString();
    }

    // Decrypt a block of text using the Hill cipher
    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int keySize = keyMatrix.length;
        StringBuilder plaintext = new StringBuilder();

        int[][] inverseMatrix = new int[keySize][keySize];
        // Calculate the inverse of the key matrix (you can use a library for this)

        for (int i = 0; i < ciphertext.length(); i += keySize) {
            String block = ciphertext.substring(i, i + keySize);
            for (int j = 0; j < keySize; j++) {
                int sum = 0;
                for (int k = 0; k < keySize; k++) {
                    sum += (block.charAt(k) - 'A') * inverseMatrix[k][j];
                }
                int decryptedChar = (sum % 26) + 'A';
                plaintext.append((char) decryptedChar);
            }
        }

        return plaintext.toString();
    }
}

