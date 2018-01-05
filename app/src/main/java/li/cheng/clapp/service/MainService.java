package li.cheng.clapp.service;

import android.app.Service;
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


}
