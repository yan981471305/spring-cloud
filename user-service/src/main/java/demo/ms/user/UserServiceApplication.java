package demo.ms.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@PropertySource(value = {"errorcode.properties"}, encoding = "utf-8")
@ComponentScan(basePackages = { "demo.ms.common","demo.ms.user"})
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
