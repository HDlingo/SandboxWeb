package edu.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author jqChen
 * @Date 2022/11/23
 * @Github https://github.com/KyrinChen
 */
@SpringBootApplication
// 这个注解开启swagger2,swagger2配置已在commons工程做好，这里直接开启就行
@EnableSwagger2
public class HibernateJpaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(HibernateJpaDemoApplication.class, args);
    }

}
