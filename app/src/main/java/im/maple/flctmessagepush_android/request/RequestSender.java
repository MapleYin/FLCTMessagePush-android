package im.maple.flctmessagepush_android.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.io.IOException;

import im.maple.flctmessagepush_android.request.Converter.JsonConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by maple on 2017/6/17.
 */

public class RequestSender<serviceT> {
    private Context context;
    private static String token = null;
    protected Retrofit retrofit;

    private serviceT service;

    public RequestSender(Context ctx,Class <serviceT> aclass) {
        context = ctx;
        createRetrofit();
        service = this.retrofit.create(aclass);
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.99.210:3003/")
                .client(genericClient())
                .addConverterFactory(JsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient genericClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("authorization", "Bearer "+token)
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();
    }


    <T> void sendData(Call<T> call, Callback<T> callback) {
        call.enqueue(callback);
    }

    void saveToken(String token) {
        RequestSender.token = token;
        SharedPreferences userPerereces = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPerereces.edit();
        editor.putString("loginToken",token);
        editor.apply();

        createRetrofit();

    }


    protected serviceT getService() {
        return this.service;
    }

}
