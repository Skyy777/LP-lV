import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESExample {

    // Encryption
    public static String encrypt(String data, String key) throws Exception {

        System.out.println("\n--- Encryption Process ---");

        // Convert key
        DESKeySpec keySpec = new DESKeySpec(key.getBytes());
        System.out.println("Key Bytes: " + Arrays.toString(key.getBytes()));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        // Convert plaintext
        byte[] plainBytes = data.getBytes();
        System.out.println("Plain Text Bytes: " + Arrays.toString(plainBytes));

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        System.out.println("Encrypted Bytes: " + Arrays.toString(encryptedBytes));

        // Convert to Base64
        String encoded = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Base64 Encoded: " + encoded);

        return encoded;
    }

    // Decryption
    public static String decrypt(String data, String key) throws Exception {

        System.out.println("\n--- Decryption Process ---");

        // Convert key
        DESKeySpec keySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        // Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        System.out.println("Decoded Base64 Bytes: " + Arrays.toString(decodedBytes));

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        System.out.println("Decrypted Bytes: " + Arrays.toString(decryptedBytes));

        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // User Input
            System.out.print("Enter text: ");
            String text = sc.nextLine();

            System.out.print("Enter 8-character key: ");
            String key = sc.nextLine();

            if (key.length() != 8) {
                System.out.println("Key must be exactly 8 characters!");
                return;
            }

            // Encrypt
            String encrypted = encrypt(text, key);
            System.out.println("\nFinal Encrypted Text: " + encrypted);

            // Decrypt
            String decrypted = decrypt(encrypted, key);
            System.out.println("\nFinal Decrypted Text: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}