package marketplace.ProjetJ2EE_SpringBoot.Functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
