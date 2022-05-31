package holanswide.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/22 21:49
 */
// 使用注解创建对象
@Component("admin")
public class Admin extends User{
    @Value(value = "ADMINCODE001")
    private String code;

    @Override
    public String toString() {
        return super.toString()+" "
                +this.getCode();
    }
    public void test() {
        System.out.println("> Admin test...");
    }

    public Admin() {
    }

    public Admin(String uid, String username, String password, int type, String code) {
        super(uid, username, password, type);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
