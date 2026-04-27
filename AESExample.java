import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class AESExample {

    // Generate AES Key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES-128
        return keyGen.generateKey();
    }

    // Encryption
    public static String encrypt(String data, SecretKey key) throws Exception {

        System.out.println("\n--- Encryption Process ---");

        // Show key
        byte[] keyBytes = key.getEncoded();
        System.out.println("Secret Key (Bytes): " + Arrays.toString(keyBytes));

        // Plain text bytes
        byte[] plainBytes = data.getBytes();
        System.out.println("Plain Text Bytes: " + Arrays.toString(plainBytes));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Encrypt
        byte[] encryptedBytes = cipher.doFinal(plainBytes);
        System.out.println("Encrypted Bytes: " + Arrays.toString(encryptedBytes));

        // Convert to Base64
        String encoded = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Base64 Encoded: " + encoded);

        return encoded;
    }

    // Decryption
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {

        System.out.println("\n--- Decryption Process ---");

        // Decode Base64
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        System.out.println("Decoded Base64 Bytes: " + Arrays.toString(decodedBytes));

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decrypt
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        System.out.println("Decrypted Bytes: " + Arrays.toString(decryptedBytes));

        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // User input
            System.out.print("Enter text: ");
            String text = sc.nextLine();

            // Generate key
            SecretKey key = generateKey();

            System.out.println("\nGenerated AES Key (Base64): " +
                    Base64.getEncoder().encodeToString(key.getEncoded()));

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