package im.maple.flctmessagepush_android.Manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by maple on 2017/8/26.
 */

public class UserManager extends BaseManager {

    private static UserManager manager = null;
    private String token = null;
    private SharedPreferences userPerereces = null;

    public UserManager(Context context) {
        userPerereces = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        token = userPerereces.getString("loginToken",null);
    }

    public boolean isLogin() {
        if (token != null) {
            return true;
        }
        return false;
    }

    public String getToken() {
        return token;
    }
}