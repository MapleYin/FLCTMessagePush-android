package im.maple.flctmessagepush_android.request;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import im.maple.flctmessagepush_android.Service.MessageService;
import im.maple.flctmessagepush_android.entity.Message;
import im.maple.flctmessagepush_android.entity.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by maple on 2017/6/17.
 */


public class MessageSender extends RequestSender<MessageService> {

    public MessageSender(Context ctx) {
        super(ctx,MessageService.class);
    }

    public void sendMessage(String content, String fromAddress) {
        Message message = new Message();
        message.content = content;
        message.timeInterval = (new Date()).getTime();
        message.fromAddress = fromAddress;

        sendData(getService().postMessage(message), new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                Log.d(TAG, "onResponse: "+response.body().code);
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {

            }
        });
    }
}
