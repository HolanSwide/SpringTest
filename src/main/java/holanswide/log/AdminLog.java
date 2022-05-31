package holanswide.log;

import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/5/22 22:18
 */

@Component(value = "adminlog")
public class AdminLog implements Service {
    @Override
    public void add() {
        System.out.println("| Admin Add");
    }

    @Override
    public void del() {
        System.out.println("| Admin Del");
    }

    @Override
    public void upd() {
        System.out.println("| Admin Upd");
    }

    @Override
    public void que() {
        System.out.println("| Admin Que");
    }
}
