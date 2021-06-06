package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.magma.cmsproject.domain.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static za.co.magma.cmsproject.constants.Constants.UPLOAD_FOLDER;

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

    @GetMapping("/upload")
    String uploadFile(Model model) {
        return "upload";
    }

    @PostMapping("/upload")
    String uploadFilePost(Model model, @RequestParam("files") MultipartFile file,
                          RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("files", file);
        System.out.println(file);
        return "redirect:upload";
    }

    @GetMapping("/tinymce")
    String tinyMCEExample(Model model) {
        model.addAttribute("person", new Person());
        return "tinymce";
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

    @RequestMapping("/welcome")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "welcome";
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
