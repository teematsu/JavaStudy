package javastudy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashTest {

    @Test
    public void sha1() throws NoSuchAlgorithmException {
        String toEncode = "abc123";
        byte[] dataBytes = toEncode.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashedBytes = md.digest(dataBytes);
        String hashHex = toHexString(hashedBytes);
        assertThat(hashHex, is("6367c48dd193d56ea7b0baad25b19455e529f5ee")); // phpのsha1で取得した値と比較
    }

    @Test
    public void sha256() throws NoSuchAlgorithmException {
        String toEncode = "abc123";
        byte[] dataBytes = toEncode.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(dataBytes);
        String hashHex = toHexString(hashedBytes);
        assertThat(hashHex, is("6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090")); // phpのhash("sha256", "abc123")で取得した値と比較
    }
    
    private String toHexString(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                    String hex = String.format("%02x", b);
                    sb.append(hex);
            }
            return sb.toString();
    }
}