    package net.biancheng.c.filter;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.cloud.gateway.filter.GatewayFilterChain;
    import org.springframework.cloud.gateway.filter.GlobalFilter;
    import org.springframework.core.Ordered;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Component;
    import org.springframework.web.server.ServerWebExchange;
    import reactor.core.publisher.Mono;
    import java.util.Date;
    /**
    * 自定义全局网关过滤器（GlobalFilter）
    */
    @Component
    @Slf4j
    public class MyGlobalFilter implements GlobalFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info("进入自定义的全局过滤器 MyGlobalFilter" + new Date());
            String uname = exchange.getRequest().getQueryParams().getFirst("uname");
            if (uname == null) {
                log.info("参数 uname 不能为 null！");
                exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                return exchange.getResponse().setComplete();
            }
            return chain.filter(exchange);
        }
        @Override
        public int getOrder() {
            //过滤器的顺序，0 表示第一个
            return 0;
        }
    }