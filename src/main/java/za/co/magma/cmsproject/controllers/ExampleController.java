package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ExampleController {
    @RequestMapping("/hello")
    String myExample1() {
        return "hello";
    }

    @RequestMapping("/tourism")
    String myTest() {

//        return "site1/Department of Tourism";
        return "site1/index";
    }

    @RequestMapping("/ms")
    String testMicrosoft() {

        return "xtests/Microsoft Community";
    }
}
