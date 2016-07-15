package com.dalbo.jajanon.Core;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alkaaf on 7/13/2016.
 */
public class Hash {
    public static String sha256(String val){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte hash[] = md.digest(val.getBytes("UTF-8"));
            return String.format("%0"+(hash.length*2)+'x',new BigInteger(1,hash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String saltHash(String val){
        String salt = "katok kolor kuning klombor klombor kintir nang kali";
        return sha256(sha256(salt) + sha256(val));
    }
}
