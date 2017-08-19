package im.maple.flctmessagepush_android.request.Converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by mapleiny on 2017/7/24.
 */

public class JsonConverterFactory extends Converter.Factory {
    public static JsonConverterFactory create() {
        return create(new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create());
    }

    public static JsonConverterFactory create(Gson gson) {
        return new JsonConverterFactory(gson);

    }

    private final Gson gson;

    private JsonConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        try {
            return new JsonResponseBodyConverter<>(gson, adapter); //响应
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        try {
            return new JsonRequestBodyConverter<>(gson, adapter); //请求
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
