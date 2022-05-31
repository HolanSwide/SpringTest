package holanswide.pojo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/22 21:31
 */

// 使用配置文件创建对象bean
public class User {
    @Value(value = "0000")
    private String uid;
    @Value(value = "testuser")
    private String username;
    @Value(value = "testpasswd")
    private String password;
    @Value(value = "0")
    private int type;


    public void test() {
        System.out.println("> User Test...");
    }
    @Override
    public String toString() {
        return this.uid+" "+
                this.username+" "+
                this.password+" "+
                String.valueOf(this.type)+" ";
    }

    public User() {
    }

    public User(String uid, String username, String password, int type) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
