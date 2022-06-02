package holanswide.annoBean;

import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author ：holan
 * @description：TODO
 * @date ：2022/6/2 21:45
 */

@Configuration
@ComponentScan("holanswide.annoBean")
public class Context {
    @Bean
    public b1 getB1() { return new b1(); }
    @Bean
    public b2 getB2() { return new b2(); }
}
