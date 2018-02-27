package li.cheng.clapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import li.cheng.clapp.CActivity;
import li.cheng.clapp.R;
import li.cheng.clapp.bean.User;
import li.cheng.clapp.bean.UserSp;

public class LoginActivity extends CActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    EditText etv_phone;
    EditText etv_pwd;

    private void initView() {
        etv_phone= (EditText) findViewById(R.id.etv_phone);
        etv_pwd= (EditText) findViewById(R.id.etv_pwd);
    }

    public void Login(View view) {
        String phone=etv_phone.getText().toString();
        String pwd=etv_pwd.getText().toString();
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
//            WToast.show(mActivity,"请输入手机号和密码");
        }else {
            User user=new User();
            user.setPhone(phone);
            user.setPassword(pwd);
//
//            BmobQuery<User> query = new BmobQuery<User>();
//            query.addWhereEqualTo(phone, "phone");
//            query.addWhereEqualTo(pwd, "password");
//            query.findObjects(new FindListener<User>() {
//                @Override
//                public void done(List<User> list, BmobException e) {
//                    if(list.isEmpty()){
//                        WToast.show(mActivity,"请检查手机号或密码是否正确");
//                    }else{
//                        User login_user=list.get(0);
//                        UserSp.save(mActivity,login_user);
//                    }
//                }
//            });

            BmobQuery<User> query = new BmobQuery<User>();
            query.addWhereEqualTo(phone, "phone");
            query.addWhereEqualTo(pwd, "password");
            query.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if(list.isEmpty()){
//                        WToast.show(mActivity,"请检查手机号或密码是否正确");
                    }else{
                        User login_user=list.get(0);
                        UserSp.save(mActivity,login_user);
                    }
                }
            });
        }
    }
}
