package im.maple.flctmessagepush_android.request.Converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import im.maple.flctmessagepush_android.util.Encrypted;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by mapleiny on 2017/7/24.
 */

public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    private final Encrypted encrypted = new Encrypted();
    /**
     * 构造器
     */

    public JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) throws Exception {
        this.gson = gson;
        this.adapter = adapter;
    }


    @Override
    public RequestBody convert(T value) throws IOException {
        //加密

        String jsonString = gson.toJson(value);

        String encodeString = null;
        try {
            encodeString = encrypted.encode(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return RequestBody.create(MEDIA_TYPE, encodeString);
    }
}

