package holanswide.annoBean;

import holanswide.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/6/2 21:50
 */

public class b2 {
    @Autowired
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public b2() {
    }

    public b2(User user) {
        this.user = user;
    }
}
