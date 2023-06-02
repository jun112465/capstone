package sejong.capstone.team13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/testing")
    @ResponseBody
    public String test(){
        return "hello world!";
    }
}
