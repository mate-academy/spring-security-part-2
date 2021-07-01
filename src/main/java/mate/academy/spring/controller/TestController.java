package mate.academy.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/admin")
    public String helloAdmin() {
        return "hello admin";
    }

    @GetMapping("/user")
    public String helloUser() {
        return "hello user";
    }
}
