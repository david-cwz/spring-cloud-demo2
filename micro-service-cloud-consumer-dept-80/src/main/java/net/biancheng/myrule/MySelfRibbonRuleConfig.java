    package net.biancheng.myrule;
    import com.netflix.loadbalancer.IRule;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    /**
    * 定制 Ribbon 负载均衡策略的配置类
    * 该自定义 Ribbon 负载均衡策略配置类 不能在 net.biancheng.c 包及其子包下
    * 否则所有的 Ribbon 客户端都会采用该策略，无法达到特殊化定制的目的
    */
    @Configuration
    public class    MySelfRibbonRuleConfig {
        @Bean
        public IRule myRule() {
            //自定义 Ribbon 负载均衡策略
            return new MyRandomRule(); //自定义，随机选择某一个微服务，执行五次
        }
    }