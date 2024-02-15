package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //로컬호스트 8080에 들어오면 호출되는 것
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
