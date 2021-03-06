package im.maple.flctmessagepush_android.util;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by maple on 2017/7/23.
 */

public class Encrypted {
    public static final String secretKey = "uguesswhatithink";
    public static final String secretIV = "uneverknowthatit";

    Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
    Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

    public Encrypted() throws Exception{
        byte[] key = secretKey.getBytes("UTF-8");
        byte[] iv = secretIV.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec IVSpec = new IvParameterSpec(iv);
        encryptCipher.init(Cipher.ENCRYPT_MODE,skeySpec,IVSpec);
        decryptCipher.init(Cipher.DECRYPT_MODE,skeySpec,IVSpec);
    }

    public String encode(String message) throws Exception {
        byte[] messageBytes = message.getBytes("UTF-8");
        byte[] encrypted = encryptCipher.doFinal(messageBytes);

        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    public String decode(String message) throws Exception {

        byte[] decode = decryptCipher.doFinal(Base64.decode(message,Base64.DEFAULT));

        return new String(decode, "UTF8");
    }
}
