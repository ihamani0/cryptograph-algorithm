import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

public class RSAA {

    public static void main(String[] args) {

        RSAA s = new RSAA(8);

        // stringToByteArray("aaaa");
        byte[] msg = stringToByteArray("aaaa");
        byte[] enc = s.encryptByte(msg);

        System.out.println(s.E + " = " + s.NN);

        for (byte b : msg) {
            System.out.println("char " + b);
        }

        for (byte b : enc) {
            System.out.println("enc " + b);
        }

        // int N = 4;
        // RSAA key = new RSAA(N);

        // String s = "a";
        // byte[] bytes = s.getBytes();
        // BigInteger message = new BigInteger(bytes);

        // System.out.println(message);

        // // // encrypt and decrypt
        // BigInteger encrypt = key.encrypt(message);
        // BigInteger decrypt = key.decrypt(encrypt);
        // System.out.println("message = " + message);
        // System.out.println("encrypted = " + encrypt);
        // System.out.println("decrypted = " + decrypt);

    }

    

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger[] privateKey = new BigInteger[2];
    private BigInteger[] publicKey = new BigInteger[2];
    public BigInteger NN;
    public BigInteger E;

    // generate an N-bit (roughly) public and private key
    RSAA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random); // generate a random prime number wth N is the number of
                                                                // bit
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one)); // phi =(p - 1) * (q - 1)

        NN = p.multiply(q); // n = p * q

        
        int e = generatepublickey(phi);
        E = E.valueOf(e);
        publicKey[0] = E;
        publicKey[1] = NN;

        privateKey[0] = E.modInverse(phi);
        privateKey[1] = NN;

    }

    public static int generatepublickey(BigInteger phi) {

        for (int i = 2; i < phi.intValue(); i++) {
            if (GCD(phi.intValue(), i) == 1) {
                return i;
            }
        }

        return -1;
    }

    public static int GCD(int a ,int b){
        if(b==0){
            return a;
        }
    return GCD(b , a % b);
    }


    public byte encrypt(byte cha) {
        int e = publicKey[0].intValue();
        int n = publicKey[1].intValue();
        byte enc;
        enc = (byte) ((int) (Math.pow(cha, e) % n));
        // return char.modPow(publicKey[0], publicKey[1]);
        // byte enc ;
        // BigInteger result = new BigInteger(String.valueOf(cha)).modPow(publicKey[0],
        // publicKey[1])
        // return result.toByteArray();
        return enc;
    }

    BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(privateKey[0], privateKey[1]);
    }

    public static byte[] strToByte(String txt) {
        byte[] bytes = new byte[(int) txt.length()];
        bytes = txt.getBytes();
        // bytes = Base64.getDecoder().decode(txt);
        return bytes;
    }

    // byte to string
    private static String encode(byte[] encypteData) {
        return Base64.getEncoder().encodeToString(encypteData);
    }

    // string to byte
    private static byte[] decode(String Data) {
        return Base64.getDecoder().decode(Data);
    }



    private static byte[] stringToByteArray(String input) {
        byte[] byteArray = new byte[input.length()];

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            byteArray[i] = (byte) c; // Casting char to byte to get ASCII value
        }

        return byteArray;
    }

    byte[] encryptByte(byte[] msg) {
        byte[] byteEncArray = new byte[msg.length];

        for (int i = 0; i < msg.length; i++) {
            byteEncArray[i] = encrypt(msg[i]);
        }

        return byteEncArray;
    }

}
