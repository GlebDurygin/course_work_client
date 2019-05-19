package com.coursework.client.service;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionService {
    private static EncryptionService instance;

    private EncryptionService() {
    }

    public static synchronized EncryptionService getInstance() {
        if (instance == null) {
            instance = new EncryptionService();
        }
        return instance;
    }

    private SecretKey secretKey;

    public byte[] encrypt(byte[] message) {
        return encryption(message, Cipher.ENCRYPT_MODE);
    }

    public byte[] decrypt(byte[] message) {
        return encryption(message, Cipher.DECRYPT_MODE);
    }

    private byte[] encryption(byte[] message, int mode) {
        if (secretKey == null) {
            createSecretKey();
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(mode, this.secretKey);
            return cipher.doFinal(message);

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException
                | IllegalBlockSizeException | BadPaddingException e) {
            e.getMessage();
        }
        return null;
    }

    private void createSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);

            this.secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
