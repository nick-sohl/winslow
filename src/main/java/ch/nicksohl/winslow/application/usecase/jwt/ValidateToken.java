package ch.nicksohl.winslow.application.usecase.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;

public class ValidateToken {

    private static final Base64.Decoder DECODER = Base64.getUrlDecoder();
    private static final Base64.Encoder ENCODER = Base64.getUrlEncoder().withoutPadding();

    private static final String HMAC_ALG = "HmacSHA512";
    private final SecretKey verificationKey;

    // Provide a stable key via configuration (same secret used to sign)
    public ValidateToken(String secret) {
        if (secret == null || secret.isBlank()) {
            throw new IllegalArgumentException("JWT secret must not be null/blank");
        }
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.verificationKey = new SecretKeySpec(decodedKey, "HmacSHA512");
    }

    /**
     * Validates the given JWT token against the UserDetails object.
     *
     * @param token the encoded JWT token
     * @param userDetails the user details (from your authentication system)
     * @return true if token is valid (subject + signature + expiry)
     */
    public boolean validate(String token, UserDetails userDetails) {
        try {
            // * 1. Split into header, payload, and signature
            // ? Structure: Ensuring the token has the standard three parts (header, payload, signature) separated by dots.
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                System.out.println("Invalid JWT format");
                return false;
            }

            String encodedHeader = parts[0];
            String encodedPayload = parts[1];
            String encodedSignature = parts[2];

            // * 2. Converting the Base64URL encoded header and payload back into JSON
            // ? Decode header to verify algorithm matches expectation
            String headerJson = new String(DECODER.decode(encodedHeader), StandardCharsets.UTF_8);
            JSONObject header = new JSONObject(headerJson);
            String alg = header.optString("alg", null);
            if (!"HS512".equalsIgnoreCase(alg)) {
                System.out.println("Unexpected JWT alg: " + alg + " (expected HS512)");
                return false;
            }

            // ? Decode payload JSON
            String payloadJson = new String(DECODER.decode(encodedPayload), StandardCharsets.UTF_8);
            JSONObject payload = new JSONObject(payloadJson);

            // * 3. Verify username (subject)
            String tokenSubject = payload.optString("sub", null);
            String userUsername = userDetails.getUsername();
            if (tokenSubject == null || !tokenSubject.equals(userUsername)) {
                System.out.println("Token subject does not match UserDetails username");
                return false;
            }

            // * 4. Verify expiration
            long exp = payload.optLong("exp", 0);
            long now = System.currentTimeMillis() / 1000;
            if (exp < now) {
                System.out.println("Token expired at: " + new Date(exp * 1000));
                return false;
            }

            // * 5. Recalculate signature with the SAME key used for signing
            String headerPayload = encodedHeader + "." + encodedPayload;

            Mac mac = Mac.getInstance(HMAC_ALG);
            mac.init(verificationKey);
            byte[] expectedSignatureBytes = mac.doFinal(headerPayload.getBytes(StandardCharsets.UTF_8));
            String expectedSignature = ENCODER.encodeToString(expectedSignatureBytes);

            if (!expectedSignature.equals(encodedSignature)) {
                System.out.println("Invalid token signature");
                return false;
            }

            // ✅ All checks passed
            return true;

        } catch (Exception e) {
            System.err.println("Token validation error: " + e.getMessage());
            return false;
        }
    }

    public String extractUsername(String token) {
        if (token == null) {
            return null;
        }
        String[] parts = token.split("\\.");
        if (parts.length < 2) {
            return null;
        }
        try {
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
            // Try standard "sub" claim
            String sub = extractJsonStringValue(payloadJson, "sub");
            if (sub != null && !sub.isBlank()) {
                return sub;
            }
            // Fallback to "username" claim if present
            String username = extractJsonStringValue(payloadJson, "username");
            if (username != null && !username.isBlank()) {
                return username;
            }
            return null;
        } catch (IllegalArgumentException e) {
            // Bad base64 or malformed token
            return null;
        }
    }

    // Very small JSON string extractor to avoid external dependencies
    private String extractJsonStringValue(String json, String fieldName) {
        String regex = "\"\\Q" + fieldName + "\\E\"\\s*:\\s*\"([^\"]*)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}