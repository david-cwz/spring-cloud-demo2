    package net.biancheng.c.service;
    import org.springframework.cloud.openfeign.FeignClient;
    import org.springframework.stereotype.Component;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    @Component
    @FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPTHYSTRIX", fallback = DeptHystrixFallBackService.class)
    public interface DeptHystrixService {
        @RequestMapping(value = "/dept/hystrix/ok/{id}")
        public String deptInfo_Ok(@PathVariable("id") Integer id);
        @RequestMapping(value = "/dept/hystrix/timeout/{id}")
        public String deptInfo_Timeout(@PathVariable("id") Integer id);
    }