package li.cheng.clapp.bean;

import android.content.Context;

import com.wuzhou.wlibrary.storage.SPUtils;

/**
 * Created by wfy 2018/2/26 16:18.
 */

public class UserSp {

    public static final String uid="ID";
    public static final String uname="NAME";
    public static final String ubindingId="BINDINGID";


    private UserSp() {

    }

    public static void save(Context context,User user){
        SPUtils spUtils=new SPUtils(context);
        spUtils.put(uid,user.getId());
        spUtils.put(uname,user.getName());
        spUtils.put(ubindingId,user.getBindingId());
        spUtils.apply();
    }

    public static User get(Context context){
        SPUtils spUtils=new SPUtils(context);
        String id=spUtils.get(uid,"").toString();
        String name=spUtils.get(uname,"").toString();
        String bindingId=spUtils.get(ubindingId,"").toString();
        User user=new User(id,name,"","",bindingId);
        return user;
    }

}
