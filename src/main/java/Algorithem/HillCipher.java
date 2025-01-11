package Algorithem;



public class HillCipher {

    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MATRIX_SIZE = 3;


    //generate Key Matrix2x2
    private static int[][] getKeyMatrix2X2(String key) {
        // Convert the key string to a 2x2 matrix
        int[][] keyMatrix = new int[2][2];
        int k = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int index = alphabet.indexOf(key.charAt(k));
                keyMatrix[i][j] = index;
                k++;
            }
        }

        return keyMatrix;
    }
    //generate Key Matrix3x3
    private static int[][] getKeyMatrix3x3(String key) {
        // Convert the key string to a 2x2 matrix
        int[][] keyMatrix = new int[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = alphabet.indexOf(key.charAt(k));
                keyMatrix[i][j] = index;
                k++;
            }
        }
        return keyMatrix;
    }

    //multiple matrix
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



    //encrypte2x2
    public static String Encrypt2X2(String plaintext, String key) throws MyExcption {
        //generate key matrix 2x2
        int[][] keyMatrix = getKeyMatrix2X2(key);

        //Test if det of matrix has invers
        int det = determinant2x2(keyMatrix);
        if(GCD.FindGcd(det , 26) != 1){
            throw new MyExcption("The matrix is not invrsible");
        }


        if(!IsInversible2x2(determinant2x2(keyMatrix))){
            throw new MyExcption( "Matrix is Not inversible" ) ;
        }

        StringBuilder cipherText = new StringBuilder();



        //add padding plainText
        while(plaintext.length() % 2 != 0) {
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

    // decryptio2x2
    public static String Decrypte2x2(String ciphertext, String key) throws MyExcption {

        //generate key matrix 2x2
        int[][] keyMatrix = getKeyMatrix2X2(key);

        if(!IsInversible2x2(determinant2x2(keyMatrix))){
           throw new MyExcption( "Matrix is Not inversible" ) ;
        }

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

    //2x2

    //get invers for matrix
    private static int[][] invertMatrix(int[][] keyMatrix) throws MyExcption {

        int det = determinant2x2(keyMatrix);

        if(GCD.FindGcd(det , 26) != 1 || det == 0){
            throw new MyExcption("The matrix is not invrsible");
        }

        int invDet = detInverse2x2(det);


        int[][] inversMatrix = new int[2][2];
        inversMatrix[0][0] = ((keyMatrix[1][1]) * invDet) % 26;
        inversMatrix[0][1] = ( ( (-keyMatrix[0][1]) * invDet ) % 26) + 26;
        inversMatrix[1][0] = (( (-keyMatrix[1][0]) * invDet + 26) % 26) +26;
        inversMatrix[1][1] = ((keyMatrix[0][0]) * invDet) % 26;

        return inversMatrix;

    }

    //calculate det for matrix 2x2
    private static int determinant2x2(int[][] keyMatrix) {
        int det = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26;

        while (det < 0) {
            det += 26;
        }

        return det;
    }
    //check  matrix 2x2 is inversibble
    private static boolean IsInversible2x2(int det){
        return det != 0 ;
    }
    // Calculates the modular inverse of a det
    private static int detInverse2x2(int a) {

        while (a < 0) {
            a += 26;
        }

        a = a % 26;
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) {
                return x;
            }
        }
        return -1;
    }





    //Matrix3x3
    //encrypte3x3
    public static String Encrypt3x3(String plaintext, String key) throws MyExcption {

        //generate key matrix 3x3
        int[][] keyMatrix = getKeyMatrix3x3(key);

        if(!IsInversible3x3(determinant3x3(keyMatrix))){
            throw new MyExcption( "Matrix is Not inversible" ) ;
        }

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

            int[] encryptedVector = multiplyMatrix3x3(vector, keyMatrix);

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
    // decryptio3x3
    public static String Dencrypt3x3(String Ciphertext, String key) throws MyExcption {

        //generate key matrix 2x2
        int[][] keyMatrix = getKeyMatrix3x3(key);

        if(!IsInversible3x3(determinant3x3(keyMatrix))){
            throw new MyExcption( "Matrix is Not inversible" ) ;
        }

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

            int[] PlainVector = multiplyMatrix3x3(vector, inverseKeyMatrix);

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





    //matrix 3x3

    private static int determinant3x3(int[][] keyMatrix) {

        int a = keyMatrix[0][0];
        int b = keyMatrix[0][1];
        int c = keyMatrix[0][2];


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
    private static boolean IsInversible3x3(int det) {
        return det != 0;
    }

    private static int GetInvDet(int det) {

        det %= 26;

        for (int i = 0; i < 26; i++) {
            if ((det * i) % 26 == 1) {
                return i;
            }
        }
        return -1;

    }



    /*private static int[][] GetmatrixAdj(int[][] keyMatrix) {

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
    }*/

    private static int[][] GetInvKeyMatrix(int[][] keyMatrix) {

        int determinant = determinant3x3(keyMatrix);
        int detInverse = GetInvDet(determinant);

        int[][] adjugate = calculateAdjugate(keyMatrix);

        int[][] inverse = new int[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                inverse[i][j] = modular((adjugate[i][j] * detInverse));
            }
        }

        return inverse;
    }

    private static int[][] calculateAdjugate(int[][] key) {
        int[][] adjugate = new int[MATRIX_SIZE][MATRIX_SIZE];
        adjugate[0][0] = key[1][1] * key[2][2] - key[1][2] * key[2][1];
        adjugate[0][1] = key[0][2] * key[2][1] - key[0][1] * key[2][2];
        adjugate[0][2] = key[0][1] * key[1][2] - key[0][2] * key[1][1];
        adjugate[1][0] = key[1][2] * key[2][0] - key[1][0] * key[2][2];
        adjugate[1][1] = key[0][0] * key[2][2] - key[0][2] * key[2][0];
        adjugate[1][2] = key[0][2] * key[1][0] - key[0][0] * key[1][2];
        adjugate[2][0] = key[1][0] * key[2][1] - key[1][1] * key[2][0];
        adjugate[2][1] = key[0][1] * key[2][0] - key[0][0] * key[2][1];
        adjugate[2][2] = key[0][0] * key[1][1] - key[0][1] * key[1][0];

        return adjugate;
    }

    private static int modular(int a){
        int mod = ( a % 26) ;
        while(mod<0){
            mod+=26;
        }
        return mod;
    }

    private static int[] multiplyMatrix3x3(int[] vector, int[][] matrix) {
        int[] result = new int[matrix.length];

        for (int i = 0; i < 3; i++) {
            int sum = 0;
            for (int j = 0; j < 3; j++) {
                sum = (sum + (vector[j] * matrix[j][i])) % 26;
            }
            result[i] = sum;
        }

        return result;
    }

    

    // private static int[][] MakeMatrixPos(int[][] InvkeyMatrix) {

    //     for (int i = 0; i < 3; i++) {

    //         for (int j = 0; j < 3; j++) {

    //             if (InvkeyMatrix[i][j] < 0) {
    //                 InvkeyMatrix[i][j] += 26;
    //             }

    //         }

    //     }
    //     return InvkeyMatrix;
    // }
}
