    package net.biancheng.c;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
    @SpringBootApplication
    @EnableEurekaClient
    public class MicroServiceCloudGateway9527Application {
        public static void main(String[] args) {
            SpringApplication.run(MicroServiceCloudGateway9527Application.class, args);
        }
    }