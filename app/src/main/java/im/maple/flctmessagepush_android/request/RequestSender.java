package im.maple.flctmessagepush_android.request;

import im.maple.flctmessagepush_android.request.Converter.JsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by maple on 2017/6/17.
 */

public class RequestSender {

    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.38:3003/")
            .addConverterFactory(JsonConverterFactory.create())
            .build();

}
