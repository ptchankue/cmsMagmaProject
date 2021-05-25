package za.co.magma.cmsproject.controllers.allocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.magma.cmsproject.domain.forms.LoginForm;
import za.co.magma.cmsproject.repository.BlogPostRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("allocation")
public class AllocationController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    private LoginForm loginForm;
    Map<String, String> params;

    private Map<String, String> setGlobalVariables(){
        params = new HashMap<>();

        params.put("messages", "9");
        params.put("notifications", "4");
        params.put("pageTitle", "");
        params.put("name", "Valerie Luna");

        return params;
    }
    @RequestMapping("/home")
    public String home(Model model) {

        Map<String, Map<String, String>> map = new HashMap<>();
        Map<String, String> w3cc = new HashMap<>();
        params = setGlobalVariables();
        w3cc.put("title", "My Car");

        map.put("w3css", w3cc);

        params.put("pageTitle", "MyCMS - Dashboard");
        model.addAttribute("parameters", params);

        return "allocation/home";
    }

}
