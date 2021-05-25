package za.co.magma.cmsproject.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.forms.LoginForm;
import za.co.magma.cmsproject.repository.BlogPostRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private BlogPostRepository blogPostRepository;

    private LoginForm loginForm;
    private Map<String, String> params;

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

        return "admin/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        params = setGlobalVariables();
        loginForm = (loginForm == null) ? new LoginForm() : loginForm;

        params.put("pageTitle", "MyCMS - Login");
        model.addAttribute("loginForm", loginForm);
        model.addAttribute("parameters", params);
        return "admin/login";
    }
    @PostMapping("/login")
    public String submitLogin(@ModelAttribute @Valid LoginForm loginForm,
                              Model model) {
        boolean success = false;
        System.out.println(loginForm);
        if (null != loginForm) {
            String email = loginForm.getEmail();
            String password = loginForm.getPassword();
            if (email.equalsIgnoreCase("ptchankue@gmail.com") &&
                password.equalsIgnoreCase("admin")){
                success = true;
            }
        }
        params.put("pageTitle", "MyCMS - Login");
        model.addAttribute("loginForm", loginForm);
        model.addAttribute("success", success);
        if(success) {
            // pass user date here
            return "redirect:/admin/home";
        }
        model.addAttribute("parameters", params);
        return "admin/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        params = setGlobalVariables();
        params.put("pageTitle", "MyCMS - Register");
        model.addAttribute("parameters", params);

        return "admin/register";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        params = setGlobalVariables();
        params.put("pageTitle", "Posts");

        model.addAttribute("parameters", params);
        model.addAttribute("blogposts", blogPostRepository.findAll());
        return "admin/posts";
    }

    @GetMapping("/generic")
    public String generic(Model model) {
        params = setGlobalVariables();
        params.put("pageTitle", "Generic");

        model.addAttribute("pageTitle", "MyCMS - Generic");
        model.addAttribute("parameters", params);
        return "admin/admin";
    }

    /**
     *  Just to understand how to make this generic enough
     * @param model
     * @return
     */
    @GetMapping("/test/site")
    public String viewSite(Model model) {
        params = setGlobalVariables();

        params.put("pageTitle", "CMS1");
        params.put("theme", "cms1");

        model.addAttribute("parameters", params);
        return params.get("theme") + "/index";
    }
}
