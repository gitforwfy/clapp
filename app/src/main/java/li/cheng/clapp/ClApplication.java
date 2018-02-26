package li.cheng.clapp;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by wfy 2018/1/5 13:24.
 */

public class ClApplication extends Application{
    //鹰眼id
    public static final long serviceId=157886;
    //bmob appid
    public static final String bmobId="991ce92febd7a11d4382f65332e5a376";
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, bmobId);
    }
}
