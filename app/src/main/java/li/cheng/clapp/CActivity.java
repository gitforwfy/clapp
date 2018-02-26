package li.cheng.clapp;

import android.os.Bundle;

import com.wuzhou.wlibrary.page.BaseActivity;

import li.cheng.clapp.bean.User;
import li.cheng.clapp.bean.UserSp;

/**
 * Created by wfy 2018/2/26 16:48.
 */

public class CActivity extends BaseActivity {
    public User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserSp.get(mActivity);
    }
}
