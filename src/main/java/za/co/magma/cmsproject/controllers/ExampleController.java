package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ExampleController {
    @RequestMapping("/hello")
    String myExample1() {
        return "hello";
    }

    @RequestMapping("/tourism")
    String myTest(Model model) {

        String[] continents = {
                "Africa", "Antarctica", "Asia", "Australia",
                "Europe", "North America", "Sourth America"
        };
//        return "site1/Department of Tourism";
        String[] menus = {

        };

        model.addAttribute("continents", continents);

        return "site1/index";
    }

    @RequestMapping("/ms")
    String testMicrosoft() {

        return "xtests/Microsoft Community";
    }

    @RequestMapping("/rental")
    String testRental() {

        return "xtests/rental";
    }
    @RequestMapping("/{templateName}")
    String testGeneric(@PathVariable String templateName) {

        String temp = "xtests/" + templateName;
        return temp;
    }
}
