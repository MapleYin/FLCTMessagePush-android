package im.maple.flctmessagepush_android.entity;

/**
 * Created by mapleiny on 2017/7/24.
 */

public class Result<T> {
    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
