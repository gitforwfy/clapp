package li.cheng.clapp;

import android.app.Activity;
import android.os.Bundle;

import li.cheng.clapp.bean.User;
import li.cheng.clapp.bean.UserSp;

/**
 * Created by wfy 2018/2/26 16:48.
 */

public class CActivity extends Activity {
    protected User user;
    protected Activity mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        user=UserSp.get(mActivity);
    }
}
