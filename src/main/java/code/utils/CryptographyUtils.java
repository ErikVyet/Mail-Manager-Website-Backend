package code.utils;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CryptographyUtils {

    private static final String ALPHANUMERIC_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
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

    public String generateApiKey() throws Exception {
        int capacity = 256;
        String prefix = "ak_dev_";
        StringBuilder apiKey = new StringBuilder(prefix);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < capacity - prefix.length(); i++) {
            apiKey.append(
                CryptographyUtils.ALPHANUMERIC_POOL.charAt(
                    secureRandom.nextInt(CryptographyUtils.ALPHANUMERIC_POOL.length())
                )
            );
        }
        return apiKey.toString();
    }
    
}