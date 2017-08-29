package im.maple.flctmessagepush_android.request;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import im.maple.flctmessagepush_android.Service.UserService;
import im.maple.flctmessagepush_android.entity.Result;
import im.maple.flctmessagepush_android.util.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by maple on 2017/8/28.
 */

public class UserSender extends RequestSender<UserService> {
    public UserSender(Context ctx) {

        super(ctx,UserService.class);
    }


    public void validateUser(String username, String password, final Transaction<Boolean> action) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        Log.i(TAG, "Login info"+map.toString());
        sendData(getService().login(map), new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if (response.body().code == 0) {
                    String token = response.body().data;
                    saveToken(token);
                    Log.i(TAG, "login token: "+response.body().data);
                    action.perform(true);
                } else {
                    action.perform(false);
                }

                Log.i(TAG, "login Response: "+response.toString());
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                action.perform(false);
                Log.i(TAG, "login error: "+t.toString());
            }
        });
    }
}
