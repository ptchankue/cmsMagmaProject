package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.Person;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ExampleController {
    @GetMapping("/hello")
    String viewHelloPage(Model model) {
        model.addAttribute("person", new Person());
        return "hello";
    }

    @PostMapping("/hello")
    String addHelloPage(@ModelAttribute("person") Person person) {
        System.out.println(person);
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
    @RequestMapping("/tmp/{templateName}")
    String testGeneric(@PathVariable String templateName, Model model) {

        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> w3cc = new HashMap<>();
        w3cc.put("title", "My Car");

        map.put("w3css", w3cc);

        model.addAttribute("values", map.get(templateName));
        String temp = "xtests/" + templateName;
        return temp;
    }
}
