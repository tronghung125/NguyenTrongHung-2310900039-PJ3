package k23cnt3.nth.lab2.pkg_annotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello,SpringBoot";
    }
}
