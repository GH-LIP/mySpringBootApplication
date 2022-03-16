package com.lip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class MySpringBootApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

    @ResponseBody
    @GetMapping("/sayHello/{name}")
    public String sayHello (@PathVariable(value = "name") String name) {
        return String.format("Hello %s!", name);
    }

}
