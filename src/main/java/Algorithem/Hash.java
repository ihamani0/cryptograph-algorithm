package Algorithem;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {


    public static String Hash(String text, String instance) throws  NoSuchAlgorithmException {
        MessageDigest hashD = MessageDigest.getInstance(instance);
        hashD.update(text.getBytes()); //
        return bytesToHex(hashD.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder Hash = new StringBuilder();
        for (byte b : bytes) {
            Hash.append(String.format("%02x", b));
        }
        return Hash.toString();
    }


}
