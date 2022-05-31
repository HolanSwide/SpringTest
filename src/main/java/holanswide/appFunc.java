package holanswide;

import holanswide.mapper.UserMap;
import holanswide.mapper.UserMapImp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/22 21:23
 */

public class appFunc {
    public static void main(String[] args) {
        // 1. 指定Spring配置文件名称
        String config = "beans.xml";
        // 2. 创建容器
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        // 3. 获取对象 getBean("id")方法
        App app = (App) ac.getBean("app");
        // 4. 使用对象
        app.func1();
//        -----------------------------------------------------------------------------------
//        User user = (User) ac.getBean("user");
//        user.test();
////        -----------------------------------------------------------------------------------
//        Admin admin = (Admin) ac.getBean("admin");
//        admin.test();
//        System.out.println("> "+admin.toString());
////        -----------------------------------------------------------------------------------
//        Service adminLog = (Service) ac.getBean("adminlog");
//        adminLog.add();
//        adminLog.del();
//        -----------------------------------------------------------------------------------

//        -----------------------------------------------------------------------------------
        // 5. 获取对象信息
        /* 获取对象数量 */
        int cnt = ac.getBeanDefinitionCount();
        System.out.println("> cnt: " + cnt);
        /* 获取对象在配置文件中的id */
        String names[] = ac.getBeanDefinitionNames();
        System.out.print("> name: ");
        for (String name : names)
            System.out.print(" | > " + name+"\n");
    }

    @Test
    public void testSpringMybatis() throws Exception {
        // 1. 指定Spring配置文件名称
        String config = "beans.xml";
        // 2. 创建容器
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        UserMapImp udi = ac.getBean("userMapImp", UserMapImp.class);
        System.out.println(udi.SearchAll());
    }
    
    @Test
    public void testSimpleMybatis() throws IOException {
        String url = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(url);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.执行 sql 语句
        SqlSession sqlss = sqlSessionFactory.openSession();
        UserMap mapper = sqlss.getMapper(UserMap.class);
        System.out.println(mapper.SearchAll());
    }
}
