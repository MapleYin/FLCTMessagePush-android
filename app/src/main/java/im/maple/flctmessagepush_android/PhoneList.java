package im.maple.flctmessagepush_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by maple on 2017/6/16.
 */

public class PhoneList extends Fragment {
    public static PhoneList newInstance() {
        PhoneList fragment = new PhoneList();
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
