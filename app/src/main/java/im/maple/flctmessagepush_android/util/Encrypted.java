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

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

    public Encrypted() throws Exception{
        byte[] key = secretKey.getBytes("UTF-8");
        byte[] iv = secretIV.getBytes("UTF-8");
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec IVSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE,skeySpec,IVSpec);
    }

    public String encode(String message) throws Exception {
        byte[] messageBytes = message.getBytes("UTF-8");
        byte[] encrypted = cipher.doFinal(messageBytes);

        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }
}
