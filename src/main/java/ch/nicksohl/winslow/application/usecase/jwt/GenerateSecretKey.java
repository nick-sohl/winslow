package ch.nicksohl.winslow.application.usecase.jwt;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class GenerateSecretKey {

    // Generate Key
    private static Key getKeyFromKeyGenerator(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }
}
