package holanswide.log;

import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/23 19:46
 */

@Component("selflog")
public class SelfLog {
    public void selfFunc() {
        System.out.println("| Put some update here...");
    }
}
