import java.math.BigInteger;
// 2.3 Real Example
// by: Jackson Cooper, Cameron Harmon


public class CooperJacksonHarmonCameronReal {

    public static BigInteger[] encryptWithPrint() {
        BigInteger[] arr = new BigInteger[3];

        // Define e as a BigInteger
        BigInteger e = new BigInteger("65537");

        String hexP = "b6fa23706cb1881090a895dd8c0321ba52004073" +
        "39e487fa3d554541ff9590277c1343f53e30da7fa7a968f7989a60e" +
        "7ad996a22ae443109a0421ece3b9c29cda22edeab53a79dfef83b10" +
        "1ad6f06763860cfd8f3762c96d302b5d289458960223ea5ae1eddd7" +
        "1d9cf4f70f69384dbc1ee023cfebcc156b8fe5506f3c2e2498f4820" +
        "a2867ff4530a0b1af6f93fbc4f28099ed840b80b2efce3046827bd9" +
        "fd8bebbc20c7932540ef7a982a533d966bf9a399fb2f96e6acfc0ca94a9be5457d2a5";

        String hexQ = "d0bf42f8ad2cd77b17a06887497f9708bad7d4cb" +
        "a6e2d882c112913b7194d6a41ed5942819da31c23e42491e2367188" +
        "f60228ccebf566d32bc4214461e7b65ac6e956f48f00c8d233ef90e" +
        "982bbf8d5fc55f388fe33f6bd3db0eea97c4613e984348ae88592eb" +
        "70b64ecc0b33835eed89e6dc0dc8f74eb35e8db6f7e29ef27ec6d06" +
        "cc4d99f702c864b45004c105494312ffa7ecc3a902241e6d88d7912" +
        "8b6b1dd8a6b1f36a329603eca663d97269f4266d85bc0a637f648f8334cb30c224f0d";

        BigInteger p = new BigInteger(hexP, 16);
        BigInteger q = new BigInteger(hexQ, 16);
        
        BigInteger N = p.multiply(q);
        arr[0] = N;

        BigInteger m = new BigInteger("3");
        
        // Calculate the number of bits in N
        int bitLength = N.bitLength();

        // Calculate (p-1) and (q-1)
        BigInteger pMinus1 = p.subtract(BigInteger.ONE);
        BigInteger qMinus1 = q.subtract(BigInteger.ONE);
        
        // Calculate the modular inverse of e modulo ((p-1)(q-1))
        BigInteger d = e.modInverse(pMinus1.multiply(qMinus1));
        arr[1] = d;

        // Calculate ciphertext
        BigInteger ciphertext = m.modPow(e, N);
        arr[2] = ciphertext;

        // decrypt ciphertext
        BigInteger plaintext = ciphertext.modPow(d, N);
        
        // Print the result
        System.out.println("p = " + p.toString(16));
        System.out.println("q = " + q.toString(16));
        System.out.println("N = " + N.toString(16));
        System.out.println("Bit-length of N = " + Integer.toString(bitLength, 16));
        System.out.println("e = " + e.toString(16));
        System.out.println("d = " + d.toString(16));
        System.out.println("c = " + ciphertext.toString(16));
        System.out.println("m2 = " + plaintext);

        return arr;
    }

    public static void decryption(BigInteger d, BigInteger N, BigInteger ciphertext) {
        // decrypt ciphertext
        ciphertext.modPow(d, N);
    }

    
    
    public static void main(String[] args) {
        BigInteger[] dN = encryptWithPrint();
        
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            decryption(dN[0], dN[1], dN[2]);
        }

        long endTime = System.currentTimeMillis();
        float elapsedTime = (float) (endTime - startTime);
        
        BigInteger N = dN[1];

        int bitLength = N.bitLength();

        float kbps = (float) (bitLength / (elapsedTime / 1000.0) / 1000.0);
        float gbps = (float) (kbps / 1000.0);

        System.out.printf("RSA Decryptions took %.0f milliseconds", elapsedTime);
        System.out.println();
        System.out.printf("RSA Decryptions took %.3f kilobits/second ", kbps);
        System.out.println();
        System.out.printf("This speed is %e gigabit/second Internet speed", gbps);
        System.out.println();

    }
}
