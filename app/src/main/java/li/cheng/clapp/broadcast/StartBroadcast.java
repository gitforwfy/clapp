package li.cheng.clapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wfy 2017/12/29 10:50.
 */

public class StartBroadcast extends BroadcastReceiver{
    String tag=getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        switch (action){
            case Intent.ACTION_SCREEN_ON:
                Log.e(tag,"ACTION_SCREEN_ON");
                break;
            case Intent.ACTION_SCREEN_OFF:
                Log.e(tag,"ACTION_SCREEN_OFF");
                break;
        }
    }
}
