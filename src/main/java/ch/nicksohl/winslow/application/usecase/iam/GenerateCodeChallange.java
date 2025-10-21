package ch.nicksohl.winslow.application.usecase.iam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateCodeChallange {

  // A cryptographically secure message digest takes arbitrary-sized input (a
  // byte array), and generates a fixed-size output, called a digest or hash.
  public static String generateCodeChallenge(String string) {
    try {
      // Get Instance of MessageDigest Object from a Provider
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      // Convert string into byte array to update the MessageDigest with that data
      byte[] bytesArray = string.getBytes();
      // Update MessageDigest with converted string as bytesArray
      sha.update(bytesArray);
      // generate a hash with digest method of MessageDigest object
      byte[] hash = sha.digest();
      // convert bytesArray to String
      return hash.toString();
    } catch (NoSuchAlgorithmException exception) {
      System.out.println(exception.getMessage());
      // Java requires every possible code path to return a value.
      // This is why we have to return something
      return null;
    }
  }
}
