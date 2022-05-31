package holanswide.log;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/22 22:20
 */

// 日志类
@Component("log")
public class Log implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        // 在切入点之前执行该方法
        System.out.println("! "+target.getClass().getName()+" "+method.getName()+" works...");
    }
}
