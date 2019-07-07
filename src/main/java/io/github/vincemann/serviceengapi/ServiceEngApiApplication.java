package io.github.vincemann.serviceengapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "io.github.vincemann.serviceengapi",
        "io.github.vincemann.generic.crud.lib"})
public class ServiceEngApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEngApiApplication.class, args);
    }

}
