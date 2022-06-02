package holanswide.mapper;

import holanswide.pojo.Info;
import holanswide.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/31 22:38
 */

public class UserMapImp implements UserMap {
    private SqlSessionTemplate session;
    //必须要有Set方法
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.session = sqlSessionTemplate;
    }

//    事务
    public void addThenDel(Info info) {
        addInfo(info);
        System.out.println(queryInfo().get(0).getUsername());
        delInfo(info);
        System.out.println(queryInfo());
    }

    @Override
    public List<Info> queryInfo() {
        return this.session.getMapper(UserMap.class).queryInfo();
    }

    @Override
    public void addInfo(Info info) {
        this.session.getMapper(UserMap.class).addInfo(info);
    }

    @Override
    public void delInfo(Info info) {
        this.session.getMapper(UserMap.class).delInfo(info);
    }


}
