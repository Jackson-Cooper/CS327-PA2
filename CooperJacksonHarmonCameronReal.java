import java.math.BigInteger;
// 2.3 Real Example
// by: Jackson Cooper, Cameron Harmon


public class CooperJacksonHarmonCameronReal {

    public static void encryption() {
        // Define e as a BigInteger
        BigInteger e = new BigInteger("65537");

        // p and q calculated from sage
        BigInteger p = new BigInteger("853523075843604572088273872419");
        BigInteger q = new BigInteger("877032731553992233643010599377");
        
        BigInteger N = p.multiply(q);

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
    }

    public static void encryptWithPrint() {
        // Define e as a BigInteger
        BigInteger e = new BigInteger("65537");

        // p and q calculated from sage
        BigInteger p = new BigInteger("853523075843604572088273872419");
        BigInteger q = new BigInteger("877032731553992233643010599377");
        
        BigInteger N = p.multiply(q);

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
        System.out.println("p = " + p.toString(16));
        System.out.println("q = " + q.toString(16));
        System.out.println("N = " + N.toString(16));
        System.out.println("Bit-length of N = " + bitLength);
        System.out.println("e = " + e.toString(16));
        System.out.println("d = " + d.toString(16));
        System.out.println("c = " + ciphertext);
        System.out.println("m2 = " + plaintext);
    }
    
    public static void main(String[] args) {
        encryptWithPrint();
        
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            encryption();
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = (endTime - startTime) / 1000;
        
        BigInteger p = new BigInteger("853523075843604572088273872419");
        BigInteger q = new BigInteger("877032731553992233643010599377");
        
        BigInteger N = p.multiply(q);

        int bitLength = N.bitLength();

        long kbps = bitLength / elapsedTime;

        System.out.printf("RSA Decryptions took %l milliseconds", elapsedTime);
        System.out.println();
        System.out.printf("RSA Decryptions in terms of kilobits/second %l", kbps);
        System.out.println();
        System.out.printf("This speed is %l gigabit/second Internet speed", kbps / 1000);

    }
}
