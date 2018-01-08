package li.cheng.clapp.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.wuzhou.wlibrary.page.BaseActivity;
import com.wuzhou.wlibrary.utils.WLog;

import li.cheng.clapp.bean.User;

/**
 * Created by wfy 2018/1/8 13:58.
 */

public class MyActivity extends BaseActivity {
    public User user;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        WLog.printe("MyActivity----onCreate");
        super.onCreate(savedInstanceState, persistentState);
        user=new User("程俐","0");
        User sweet_user=new User("王枫元","1");
        user.setSweet_user(sweet_user);
    }
}
