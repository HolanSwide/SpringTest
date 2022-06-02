package holanswide.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/6/2 20:57
 */

@Repository
public class Info {
    @Value(value = "0")
    private int uid;
    @Value(value = "testUsername")
    private String username;

    public Info() {
    }

    public Info(int uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
