//package Util;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Base64;
//
//public class AESUtil {
//    private static final String ALGORITHM = "AES";
//    public static SecretKey generateKey() throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
//        keyGen.init(256);
//        return keyGen.generateKey();
//    }
//
//    public static String encrypt(String data, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
//        return Base64.getEncoder().encodeToString(encryptedBytes);
//    }
//
//    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
//        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
//        return new String(decryptedBytes);
//    }
//
//    public static SecretKey getKeyFromString(String keyStr) {
//        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
//        return new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);
//    }
//
//    public static String getKeyAsString(SecretKey key) {
//        byte[] encodedKey = key.getEncoded();
//        return Base64.getEncoder().encodeToString(encodedKey);
//    }
//}