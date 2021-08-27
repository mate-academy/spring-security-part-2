package mate.academy.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    public String hello(Authentication authentication) {
        return String.format("Hello, %s! you are: %s", authentication.getName(),
                authentication.getAuthorities());
    }

    @GetMapping("/user")
    public String user(Authentication authentication) {
        return "you are USER";
    }
}
