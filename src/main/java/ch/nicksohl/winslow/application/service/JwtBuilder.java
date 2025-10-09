package ch.nicksohl.winslow.application.service;

// For generating JSON from an Object
import org.json.JSONObject;

// To create a signature with the Hmac Algorithm
import javax.crypto.Mac;
import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;

import java.security.*;
import java.util.*;

// Builder Pattern
// A clear, readable API for progressively constructing complex immutable objects.
public class JwtBuilder {
    private static final Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();
    private static final String DEFAULT_ALG = "HS512";
    private static final String DEFAULT_HMAC = "HmacSHA512";

    private JwtBuilder() {}

    // ? Builder
    public static class Builder {
        private String jwtAlg = DEFAULT_ALG;      // "HS512"
        private String hmacAlg = DEFAULT_HMAC;    // "HmacSHA512"
        private String issuer = "winslow";
        private String subject;
        private final Map<String, Object> claims = new LinkedHashMap<>();
        private long issuedAt = System.currentTimeMillis() / 1000;
        private long expiresInSeconds = 3600; // 1 hour
        private SecretKey secretKey;

        // * --- Fluent Builder Methods ----------------------------------------------------------------------------------

        public Builder algorithm(String jwtAlg, String hmacAlg) {
            this.jwtAlg = jwtAlg;
            this.hmacAlg = hmacAlg;
            return this;
        }

        public Builder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder claims(String key, Object value) {
            this.claims.put(key, value);
            return this;
        }

        public Builder issuedAt() {
            this.issuedAt = System.currentTimeMillis() / 1000; // calculate into seconds for JWT
            return this;
        }

        public Builder expiresInSeconds(long seconds) {
            this.expiresInSeconds = seconds;
            return this;
        }

        public Builder signWith(SecretKey secretKey) {
            this.secretKey = secretKey;
            return this;
        }

        // --- Build Final JWT Token -----------------------------------------------------------------------------------

        public String build() throws NoSuchAlgorithmException, InvalidKeyException {
            if (secretKey == null) {
                throw new IllegalStateException("Secret key must be provided before building JWT");
            }

            // define header in hashmap
            LinkedHashMap<String, Object> header = new LinkedHashMap<>();
            header.put("alg", jwtAlg);
            header.put("typ", "JWT");

            // define claims in hashmap
            LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
            payload.put("iss", issuer);
            payload.put("sub", subject);
            payload.put("iat", issuedAt);
            payload.put("exp", issuedAt + expiresInSeconds); // current date + seconds -> e.g., 3600 seconds = 1 hour
            payload.putAll(claims); // put another map of key-value pairs into a map -> map of claims into payload

            // convert hashmap into JSON -> convert JSON to String -> encode it to Base64 URL String
            String encodedHeader = base64UrlEncode(new JSONObject(header).toString());
            String encodedPayload = base64UrlEncode(new JSONObject(payload).toString());

            // concatenate header and payload to dot separated string
            String headerPayload = encodedHeader + "." + encodedPayload;

            // Sign encoded dot-separated header-payload String with Algorithm -> return-value is the Signature
            // ? Message Authentication Code (MAC) algorithm

            Mac mac = Mac.getInstance(hmacAlg);
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(headerPayload.getBytes(StandardCharsets.UTF_8));
            String encodedSignature = base64UrlEncode(bytes);

            // * return JWT
            // The output is three Base64-URL strings separated by dots
            // that can be easily passed in HTML and HTTP environments.
            return headerPayload + "." + encodedSignature;
        }
    }

    // ====================================================================================
    // === STATIC UTILITIES ===============================================================
    // ====================================================================================

    // Encode String with Base64 and return String
    public static String base64UrlEncode(String input) {
        return ENCODER.encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    // Encode Array of bytes with Base64 and return String
    public static String base64UrlEncode(byte[] input) {
        return ENCODER.encodeToString(input);
    }
}
