package ch.nicksohl.winslow.application.service;

// For generating JSON from an Object
import ch.nicksohl.winslow.domain.User;
import org.json.JSONObject;

// To generate a secret key
import javax.crypto.KeyGenerator;

// To create a signature with the Hmac Algorithm
import javax.crypto.Mac;

import java.nio.charset.StandardCharsets;

import java.security.*;
import java.util.*;

// Builder Pattern
// A clear, readable API for progressively constructing complex immutable objects.
public class JwtService {
    private static final String JWT_ALGORITHM = "HS512";
    private static final String HMAC_ALGORITHM = "HmacSHA512";
    private static final String MEDIA_TYPE = "JWT";

    User user;

    static Base64.Encoder encoder = Base64.getUrlEncoder();

    private JwtService() {}

    public static class Builder {

    }

    private static LinkedHashMap<String, Object> buildHeader(String algorithm) {
        LinkedHashMap<String, Object> header = new LinkedHashMap<>();

        header.put("alg", algorithm);
        header.put("typ", MEDIA_TYPE);

        return header;
    }

    private static LinkedHashMap<String, Object> buildClaims(String email, String username) {
        LinkedHashMap<String, Object> claims = new LinkedHashMap<>();

        long currentDate = new Date().getTime() / 1000; // Seconds since the "epoch"

        claims.put("iss", "winslow");
        claims.put("sub", email);
        claims.put("name", username);
        claims.put("iat", currentDate);
        claims.put("exp", currentDate + 60 * 60);

        return claims;
    }

    private static JSONObject convertToJSON(LinkedHashMap<String, Object> linkedHashMap) {
        return new JSONObject(linkedHashMap);
    }

    // Generate Key
    // TODO : Only use one secret and save it inside the .env file -> Always reuse the same secret
    private static Key getKeyFromKeyGenerator(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }


    private static String createSignature(String algorithm, String header, String claims, Key secretKey) throws NoSuchAlgorithmException {
        // encode json string of header and claims with base64 encoder into a new encoded string
        String encodedHeader = encoder.encodeToString(header.getBytes(StandardCharsets.UTF_8));
        String encodedClaims = encoder.encodeToString(claims.getBytes(StandardCharsets.UTF_8));
        // concat header and claims with separation dot
        String headerClaims = encodedHeader + "." + encodedClaims;
        // get instance of hmac algorithm
        Mac hmac = Mac.getInstance(algorithm);
        // initialize the engine class
        try {
            hmac.init(secretKey);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        byte[] signatureByteArray = hmac.doFinal(headerClaims.getBytes(StandardCharsets.UTF_8));
        return encoder.encodeToString(signatureByteArray);
    }

    /**
     *
     The output is three Base64-URL strings separated by dots
     that can be easily passed in HTML and HTTP environments.
     */
    public static String buildToken(String header, String claims, String signature) {
        return header + "." + claims + "." + signature;
    }
}
