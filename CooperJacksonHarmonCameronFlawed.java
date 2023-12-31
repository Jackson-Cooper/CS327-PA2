import java.math.BigInteger;
// 2.2 Flawed Example
// by: Jackson Cooper, Cameron Harmon

public class CooperJacksonHarmonCameronFlawed {
    public static void main(String[] args) {

        // Define N, e as a BigIntegers
        BigInteger N = new BigInteger("748567674651481801995116447644070267796617508884470018882963");
        BigInteger e = new BigInteger("65537");

        // p and q calculated from sage
        BigInteger p = new BigInteger("853523075843604572088273872419");
        BigInteger q = new BigInteger("877032731553992233643010599377");

        BigInteger m = new BigInteger("3");
        
        // Calculate the number of bits in N
        int bitLength = N.bitLength();

        // Calculate (p-1) and (q-1)
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);
        
        // Calculate the modular inverse of e modulo ((p-1)(q-1))
        BigInteger d = e.modInverse(pMinus1.multiply(qMinus1));

        // Calculate ciphertext
        BigInteger ciphertext = m.modPow(e, N);

        // decrypt ciphertext
        BigInteger plaintext = ciphertext.modPow(d, N);
        
        // Print the result
        System.out.println("N: " + N.toString(16));
        System.out.println("p: " + p.toString(16));
        System.out.println("q: " + q.toString(16));
        System.out.println("Bit-length of N: " + Integer.toString(bitLength, 16));
        System.out.println("d = e^-1 mod z = " + d.toString(16));
        System.out.println("c = m^e mod N = " + ciphertext.toString(16));
        System.out.println("m2 = c^d mod N = " + plaintext);
    }
}