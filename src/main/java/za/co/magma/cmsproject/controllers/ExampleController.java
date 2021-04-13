package za.co.magma.cmsproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @RequestMapping("/hello")
    String myExample1() {
        return "hello";
    }
}
