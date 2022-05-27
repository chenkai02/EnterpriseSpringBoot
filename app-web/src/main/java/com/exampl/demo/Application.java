package com.exampl.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.exampl.app.mapper")
@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
