package li.cheng.clapp.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wfy 2018/1/8 13:55.
 */

<<<<<<< HEAD
public class User  {
=======
public class User extends BmobObject {
>>>>>>> 903ef01fb47a5349a239519e23530c87ffd776b6
    String id;
    String name;
    String phone;
    String email;
    String bindingId;
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String id, String name, String phone, String email, String bindingId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bindingId = bindingId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }
}
