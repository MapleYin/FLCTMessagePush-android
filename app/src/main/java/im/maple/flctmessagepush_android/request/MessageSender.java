package im.maple.flctmessagepush_android.request;

import android.util.Log;

import java.util.Date;

import im.maple.flctmessagepush_android.Service.MessageService;
import im.maple.flctmessagepush_android.entity.Message;
import im.maple.flctmessagepush_android.entity.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by maple on 2017/6/17.
 */


public class MessageSender extends RequestSender{

    MessageService service = this.retrofit.create(MessageService.class);

    public MessageSender(){

    }

    public void sendMessage(String content, String fromAddress) {
        Message message = new Message();
        message.content = content;
        message.timeInterval = (new Date()).getTime();
        message.fromAddress = fromAddress;
        this.sendData(message);
    }


    private void sendData(Message message) {
        Call<Result<String>> call = service.postMessage(message);
        call.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    // success

                } else {
                    // error store and retry
                }
            }
            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
            }
        });
    }
}
