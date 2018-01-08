package li.cheng.clapp.bean;

/**
 * Created by wfy 2018/1/8 13:55.
 */

public class User {
    String id;
    String name;
    User sweet_user;

    public User(String name) {
        this.name = name;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String id, String name, User sweet_user) {
        this.id = id;
        this.name = name;
        this.sweet_user = sweet_user;
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

    public User getSweet_user() {
        return sweet_user;
    }

    public void setSweet_user(User sweet_user) {
        this.sweet_user = sweet_user;
    }
}
