import java.math.BigInteger;
import java.util.Scanner;

public class RSAExample {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter prime q: ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("3");
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        BigInteger d = e.modInverse(phi);

        System.out.println("Public Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        System.out.print("Enter message (number): ");
        BigInteger message = sc.nextBigInteger();

        BigInteger cipher = message.modPow(e, n);
        System.out.println("Encrypted: " + cipher);


        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("Decrypted: " + decrypted);
    }
}