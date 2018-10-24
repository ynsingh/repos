package server;


//package com.ehelpy.brihaspati4.mserver.utils;

/* License - read License.txt in base directory of the code distribution.
* Design - YNSingh (2016)
* Implementation -Chetna (2016), N.K.Singh(2016).
* Date, Change description - .
* Copyright 2016 : YNSingh
*/

import java.util.Random;

public class ServerUtil {

    /** Generate a random Key */
    public static int generateRandomKey() {
        int key=new Random().nextInt();
        if(key<=0) {
            do {
                key=new Random().nextInt();
            } while(key<=0);
        }
        return key;
    }

    /** Generate  random integers in the range 0..999999. */
    public static int RandomInteger() {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000000);
        return randomInt;
    }
}

