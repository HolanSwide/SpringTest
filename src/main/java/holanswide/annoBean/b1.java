package holanswide.annoBean;

import holanswide.pojo.Info;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/6/2 21:50
 */

public class b1 {
    @Autowired
    private Info info;

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public b1() {
    }

    public b1(Info info) {
        this.info = info;
    }
}
