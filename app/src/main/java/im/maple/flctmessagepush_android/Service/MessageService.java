package im.maple.flctmessagepush_android.Service;

import java.util.Date;

import im.maple.flctmessagepush_android.entity.Message;
import im.maple.flctmessagepush_android.entity.Result;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mapleiny on 2017/7/24.
 */


public interface MessageService {
    @Headers("Content-Type: application/octet-stream")
    @POST("api/message")
    Call<Result<String>> postMessage(@Body Message message);
}
