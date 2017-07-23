package im.maple.flctmessagepush_android;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import im.maple.flctmessagepush_android.util.Encrypted;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Encrypted encrypted = new Encrypted();

        Log.v(TAG, "useAppContext: "+encrypted.encode("nihao"));

        assertEquals(encrypted.encode("nihao"), "5aT48lF4Mw/0hnHvjxZnxA==");
    }
}
