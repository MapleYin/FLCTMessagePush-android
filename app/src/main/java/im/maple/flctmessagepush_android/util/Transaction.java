package im.maple.flctmessagepush_android.util;

/**
 * Created by maple on 2017/8/29.
 */

public interface Transaction<T> {
    public void perform(T params);
}