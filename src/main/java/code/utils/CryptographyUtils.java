package code.utils;

import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CryptographyUtils {

    private static CryptographyUtils instance = null;

    private CryptographyUtils() { }

    public static CryptographyUtils getInstance() {
        if (instance == null) {
            instance = new CryptographyUtils();
        }
        return instance;
    }

    public String generateSecretKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
    
}