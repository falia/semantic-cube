package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;


@Controller
public class IndexController {

    private String message = "Hello World";

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        System.out.println("hello from IndexController");
        model.put("message", this.message);
        return "index";
    }


}