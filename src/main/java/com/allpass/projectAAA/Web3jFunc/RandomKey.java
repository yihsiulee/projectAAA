package com.allpass.projectAAA.Web3jFunc;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomKey {

    private RandomKey() {
    }

    public static String getRandomPrivateKey() {
        return getRandomIntString(256, 16);
    }

    private static String getRandomIntString(int length, int radix) {
        SecureRandom random = new SecureRandom();
        BigInteger result;
        do {
            result = new BigInteger(length, random);
        } while (result.bitLength() < length);
        return result.toString(radix);
    }

}
