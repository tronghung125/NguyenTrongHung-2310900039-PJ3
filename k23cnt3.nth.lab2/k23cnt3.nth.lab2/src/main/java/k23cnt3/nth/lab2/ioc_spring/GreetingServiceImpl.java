package k23cnt3.nth.lab2.ioc_spring;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String name) {
        return "<h2>Hung [Spring Boot!] Xin chào,</h2>"
                + "<h1 style='color:red; text-align:center'>" + name + "</h1>";
    }
}
