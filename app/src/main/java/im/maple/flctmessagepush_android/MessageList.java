package im.maple.flctmessagepush_android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import im.maple.flctmessagepush_android.request.MessageSender;

/**
 * Created by maple on 2017/6/15.
 */

public class MessageList extends Fragment {
    public static MessageList newInstance() {
        MessageList fragment = new MessageList();
        MessageSender messageSender = new MessageSender();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messagelist,container,false);
    }
}
