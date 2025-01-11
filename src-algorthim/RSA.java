import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class RSA {

    private String FilePath = "/home/hmn/Desktop/Text/RsaEnc.txt";
    private int p;
    private int q;
    private int N;
    private int E;
    private int D;
    private int Phi;

    // byte to string

    private static String encode(byte[] encypteData) {
        return Base64.getEncoder().encodeToString(encypteData);
    }

    // string to byte
    private static byte[] decode(String Data) {
        return Base64.getDecoder().decode(Data);
    }

    // constrect RSA
    public RSA(int p, int q) {
        this.p = p;
        this.q = q;
    }

    public static void main(String[] args) {

        try {
            RSA s1 = new RSA(19, 7);
            // System.out.println("first -> " +s1.squareMultiply((byte) 105, 5, 133));
            s1.generateKey();
            byte[] text = s1.encrypte("helloworld");

            String txt = encode(text);
            System.out.println("encrypte  = " + txt);
            WriteInFile(txt, s1.FilePath);

            String EncryptedData = new String(Files.readAllBytes(Paths.get(s1.FilePath)));

            byte[] cipher = decode(EncryptedData);

            // for (byte b : cipher) {
            // System.out.println("byte cipher " + b);
            // }

            byte[] plain = s1.decrypte(cipher);

            System.out.println(new String(plain,java.nio.charset.StandardCharsets.UTF_8));
            // System.out.println(s1.decrypte(cipher));

        } catch (Exception e) {
            System.err.println("ex => " + e.getMessage());
        }

    }

    public void generateKey() {

        if (!IsPrime(p) || !IsPrime(q)) {
            throw new IllegalArgumentException("p and q must be prime numbers");
        }

        this.N = (this.p * this.q);
        this.Phi = (this.p - 1) * (this.q - 1);
        this.E = randomeE();

        this.D = modInvers(this.E, this.Phi);

    }

    public Boolean IsPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int randomeE() {
        for (int i = 2; i < this.Phi; i++) {
            if (GCD(this.Phi, i) == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int GCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return GCD(b, a % b);
    }

    public int modInvers(int a, int b) {

        int u0 = 1, u1 = 0;
        int m = b;
        int v0 = 0, v1 = 1;

        while (b != 0) {
            // calcule div a on b
            int div = a / b;
            int rest = a % b;

            // change value
            a = b;
            b = rest;

            int newU = u0 - div * u1;
            int newV = v0 - div * v1;

            u0 = u1;
            u1 = newU;
            v0 = v1;
            v1 = newV;

        }

        while (u0 < 0) {
            u0 += m;
        }

        return u0;

    }

    public byte[] encrypte(String plainText) {

        byte[] text_bytes = plainText.getBytes();
        byte[] NewCharByte = new byte[text_bytes.length];

        System.out.println("e =" + this.E);
        System.out.println("N =" + this.N);

        for (int i = 0; i < text_bytes.length; i++) {
            NewCharByte[i] = squareMultiply(text_bytes[i], this.E, this.N);
            System.out.println("byte plain text =" + text_bytes[i]);

        }

        return NewCharByte;
    }

    public byte[] decrypte(byte[] cipherByte) {

        byte[] NewCharByte = new byte[cipherByte.length];

        System.out.println("e =" + this.D);
        System.out.println("N =" + this.N);

        for (int i = 0; i < cipherByte.length; i++) {
            NewCharByte[i] = squareMultiply(cipherByte[i], this.D, this.N);
            System.out.println("byte cipher text =" + cipherByte[i]);

        }
        return NewCharByte;

    }

    public byte squareMultiply(byte base, int exponent, int modulus) {
        byte result = 1;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (byte) ((result * base) % modulus);
            }
            base = (byte) ((base * base) % modulus);
            exponent /= 2;
        }
        return result;
    }

    private static void WriteInFile(String Data, String FilePath) throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream(FilePath);
            fos.write(Data.getBytes(StandardCharsets.UTF_8));
            fos.close();

        } catch (Exception e) {
            System.err.println("Excption " + e);
        }
    }

    private static byte[] readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }

}
