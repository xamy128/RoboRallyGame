package utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Ammarah on 5/16/2017.
 */
public class Utils {

    private final SecureRandom random = new SecureRandom();
private String secretKey="";

    public void setSecretKey(int noOfBits,int radix){
secretKey=new BigInteger(130, random).toString(32);

    }

    public String getSecretKey() {
        return secretKey;
    }
}
