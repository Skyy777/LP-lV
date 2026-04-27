import java.math.BigInteger;
import java.util.Scanner;

public class RSAExample {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input primes
        System.out.print("Enter prime p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter prime q: ");
        BigInteger q = sc.nextBigInteger();

        // Step 1: n = p * q
        BigInteger n = p.multiply(q);
        System.out.println("\nStep 1: n = p * q = " + n);

        // Step 2: phi(n)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("Step 2: φ(n) = (p-1)(q-1) = " + phi);

        // Step 3: Choose e
        BigInteger e = new BigInteger("3");
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }
        System.out.println("Step 3: Selected e such that gcd(e, φ) = 1 → e = " + e);

        // Step 4: Compute d
        BigInteger d = e.modInverse(phi);
        System.out.println("Step 4: d = e⁻¹ mod φ(n) = " + d);

        // Keys
        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        // Message input
        System.out.print("\nEnter message (number < n): ");
        BigInteger message = sc.nextBigInteger();

        // Step 5: Encryption
        System.out.println("\n--- Encryption ---");
        System.out.println("Formula: C = M^e mod n");
        BigInteger cipher = message.modPow(e, n);
        System.out.println("C = " + message + "^" + e + " mod " + n + " = " + cipher);

        // Step 6: Decryption
        System.out.println("\n--- Decryption ---");
        System.out.println("Formula: M = C^d mod n");
        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("M = " + cipher + "^" + d + " mod " + n + " = " + decrypted);

        sc.close();
    }
}