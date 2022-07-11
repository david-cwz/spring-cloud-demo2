    package net.biancheng.c.controller;
    import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
    import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;;
    import lombok.extern.slf4j.Slf4j;
    import net.biancheng.c.service.DeptHystrixService;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import javax.annotation.Resource;
    @Slf4j
    @RestController
    @DefaultProperties(defaultFallback = "dept_Global_FallbackMethod") //全局的服务降级方法
    public class HystrixController_Consumer {
        @Resource
        private DeptHystrixService deptHystrixService;
        @RequestMapping(value = "/consumer/dept/hystrix/ok/{id}")
        public String deptInfo_Ok(@PathVariable("id") Integer id) {
            return deptHystrixService.deptInfo_Ok(id);
        }

        //在客户端进行降级
        @RequestMapping(value = "/consumer/dept/hystrix/timeout/{id}")
//        @HystrixCommand(fallbackMethod = "dept_TimeoutHandler") //为该请求指定专属的回退方法
        @HystrixCommand
        public String deptInfo_Timeout(@PathVariable("id") Integer id) {
            String s = deptHystrixService.deptInfo_Timeout(id);
            log.info(s);
            return s;
        }

        // deptInfo_Timeout方法的 专用 fallback 方法
        public String dept_TimeoutHandler(@PathVariable("id") Integer id) {
            log.info("deptInfo_Timeout 出错，服务已被降级！");
            return "C语言中文网提醒您：服务端系统繁忙，请稍后再试！（客户端 deptInfo_Timeout 专属的回退方法触发）";
        }

        /**
         * 全局的 fallback 方法，
         * 回退方法必须和 hystrix 的执行方法在相同类中
         * @DefaultProperties(defaultFallback = "dept_Global_FallbackMethod") 类上注解，请求方法上使用 @HystrixCommand 注解
         */
        public String dept_Global_FallbackMethod() {
            return "C语言中文网提醒您，运行出错或服务端系统繁忙，请稍后再试！（客户端全局回退方法触发,）";
        }

    }