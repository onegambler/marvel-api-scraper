package com.magale.roberto.scraper.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MarvelApiHashGenerator {

    public String generateHash(String timestamp, String privateKey, String publicKey) {
        try {
            String value = timestamp + privateKey + publicKey;
            MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5Encoder.digest(value.getBytes());

            StringBuilder md5 = new StringBuilder();
            for (byte md5Byte : md5Bytes) {
                md5.append(Integer.toHexString((md5Byte & 0xFF) | 0x100).substring(1, 3));
            }
            return md5.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed hash generation", e);
        }
    }

}
