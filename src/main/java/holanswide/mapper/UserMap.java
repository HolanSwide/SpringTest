package holanswide.mapper;

import holanswide.pojo.Info;
import holanswide.pojo.User;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/31 22:18
 */

public interface UserMap {
    public List<Info> queryInfo();
    public void addInfo(Info info);
    public void delInfo(Info info);

}
