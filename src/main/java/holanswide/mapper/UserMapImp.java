package holanswide.mapper;

import holanswide.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
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
    private SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<User> SearchAll() {
        return sqlSessionTemplate.getMapper(UserMap.class).SearchAll();
    }
}
