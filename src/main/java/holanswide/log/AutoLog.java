package holanswide.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/23 20:08
 */

@Component("autolog")
@Aspect
public class AutoLog {
    @Before("execution(* holanswide.pojo.User.*(..))")
    public void before() {
        System.out.println("> Auto Aspect before...");
    }
    @After("execution(* holanswide.pojo.User.*(..))")
    public void after() {
        System.out.println("> Auto Aspect after...");
    }
    @Around("execution(* holanswide.pojo.User.*(..))")
    public Object cut(ProceedingJoinPoint pjp) {
        System.out.println("> Cut here...");
        return null;
    }
}
