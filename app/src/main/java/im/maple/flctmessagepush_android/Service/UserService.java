package im.maple.flctmessagepush_android.Service;

import java.util.Map;

import im.maple.flctmessagepush_android.entity.Result;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by maple on 2017/8/28.
 */

public interface UserService {
    @POST("api/authorize")
    Call<Result<String>> login(@Body Map<String,String> loginInfo);
}
