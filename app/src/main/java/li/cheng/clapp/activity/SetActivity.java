package li.cheng.clapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import li.cheng.clapp.CActivity;
import li.cheng.clapp.R;

public class SetActivity extends CActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
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
                    Intent intent=new Intent(mActivity,LoginActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            tv_account.setText(user.getName());
        }
    }
}
