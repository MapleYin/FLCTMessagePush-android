package im.maple.flctmessagepush_android.request.Converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import im.maple.flctmessagepush_android.entity.Result;
import im.maple.flctmessagepush_android.util.Encrypted;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by mapleiny on 2017/7/24.
 */

public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Encrypted encrypted = new Encrypted();

    private final Gson mGson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) throws Exception {
        this.mGson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {

        String response = responseBody.string();
        String decodeString = "";
        try {
            decodeString = encrypted.decode(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Type typeOfT = new TypeToken<Result<T>>(){}.getType();

        T result = mGson.fromJson(decodeString,typeOfT);
        return result;
    }
}
