package za.co.magma.cmsproject.controllers.walrus;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.Person;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WalrusController {
    @GetMapping("/walrus")
    String viewWalrusPage(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("contextPath", "");

        model.addAttribute("person", new Person());
        model.addAttribute("title", "My title");
        model.addAttribute("model", m);
        return "walrus/index1";
    }
}
