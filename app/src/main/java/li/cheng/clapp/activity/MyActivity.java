package li.cheng.clapp.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuzhou.wlibrary.utils.WLog;

import li.cheng.clapp.CActivity;
import li.cheng.clapp.R;

/**
 * Created by wfy 2018/1/8 13:58.
 */

public class MyActivity extends CActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        WLog.printe("MyActivity----onCreate");
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_my);
        initView();
    }
    RelativeLayout rl_user;
    TextView tv_account;
    private void initView() {
        rl_user= (RelativeLayout) findViewById(R.id.rl_user);
        tv_account= (TextView) findViewById(R.id.tv_account);

        if(TextUtils.isEmpty(user.getId())){
            tv_account.setText("请登录");
            rl_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else{
            tv_account.setText(user.getName());
        }
    }
}
