package ch.nicksohl.winslow.application.usecase.iam;

import java.util.Random;

/**
 * code_verifier = high-entropy cryptographic random STRING using the
 * unreserved characters [A-Z] / [a-z] / [0-9] / "-" / "." / "_" / "~"
 * from Section 2.3 of [RFC3986], with a minimum length of 43 characters
 * and a maximum length of 128 characters.
 */
public class GenerateCodeVerifier {

  public static String randomStringGenerator() {
    String unreserved = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~";
    int MAX_LENGTH = 128; // max-length of String

    Random random = new Random();
    StringBuffer randomString = new StringBuffer(MAX_LENGTH);

    for (int i = 0; i <= MAX_LENGTH; i++) {
      int randomIndex = 0 + random.nextInt(unreserved.length() - 1);
      randomString.append(unreserved.charAt(randomIndex));
    }

    return randomString.toString();
  }
}
