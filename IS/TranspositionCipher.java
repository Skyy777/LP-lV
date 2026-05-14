import java.util.*;
public class TranspositionCipher {
    public static String encrypt(String text, int key) {
        int len = text.length();
        int rows = (int) Math.ceil((double) len / key);
        char[][] matrix = new char[rows][key];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                if (k < len)
                    matrix[i][j] = text.charAt(k++);
                else
                    matrix[i][j] = 'X'; // padding
            }
        }
        System.out.println("\nMatrix (Row-wise filled):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder cipher = new StringBuilder();
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                cipher.append(matrix[i][j]);
            }
        }
        return cipher.toString();
    }
    public static String decrypt(String cipher, int key) {
        int len = cipher.length();
        int rows = len / key;
        char[][] matrix = new char[rows][key];
        int k = 0;
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = cipher.charAt(k++);
            }
        }
        System.out.println("\nMatrix (Column-wise filled):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {
                text.append(matrix[i][j]);
            }
        }
        return text.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter key (columns): ");
        int key = sc.nextInt();
        String encrypted = encrypt(text, key);
        System.out.println("\nEncrypted Text: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("\nDecrypted Text: " + decrypted);
        sc.close();
    }
}