package li.cheng.clapp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by wfy 2017/12/29 10:48.
 */

public class MainService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        WLog.print("onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        WLog.print("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        WLog.print("onDestroy");
    }

    public static void StartService(Context context){
        Intent intent=new Intent(context,MainService.class);
        context.startService(intent);
    }
}
