package im.maple.flctmessagepush_android.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;

import im.maple.flctmessagepush_android.request.MessageSender;

/**
 * Created by mapleiny on 2017/8/19.
 */

public class SmsReceiver extends BroadcastReceiver {
    static int currentStatus = 0;
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String PHONE_STATE = "android.intent.action.PHONE_STATE";

    private MessageSender messageSender = null;

    public void onReceive(Context context, Intent intent) {
        if (messageSender == null) {
            messageSender = new MessageSender(context);
        }
        TelephonyManager mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        final String num = mTelephonyMgr.getLine1Number();
        if (intent.getAction().equals(SMS_RECEIVED)){
            Bundle bundle = intent.getExtras();
            if (null != bundle) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                SmsMessage[] messages = new SmsMessage[pdus.length];
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                final String sender = messages[0].getOriginatingAddress();
                final String message = sb.toString();
                this.messageSender.sendMessage(message,sender,num);
            }
        }else if( intent.getAction().equals(PHONE_STATE) ){
            final SmsReceiver self = this;
            TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            telephony.listen(new PhoneStateListener(){
                @Override
                public void onCallStateChanged(int state, String incomingNumber) {
                    super.onCallStateChanged(state, incomingNumber);
                    if (state == TelephonyManager.CALL_STATE_RINGING && currentStatus != state){
                        currentStatus = state;
                        final String sender = "来电提醒";
                        final String message = incomingNumber+" 正在呼叫你！";
                        self.messageSender.sendMessage(message,sender,num);
                    }
                }
            },PhoneStateListener.LISTEN_CALL_STATE);
        }

    }
}
