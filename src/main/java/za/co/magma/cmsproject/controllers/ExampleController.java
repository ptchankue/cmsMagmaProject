package za.co.magma.cmsproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.magma.cmsproject.cms.CmsTemplateCatalog;
import za.co.magma.cmsproject.domain.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static za.co.magma.cmsproject.utils.Constants.UPLOAD_FOLDER;

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
    String tourismGovernmentLayout(Model model) {
        addGovernmentPortalModel(model, true);
        return CmsTemplateCatalog.themeView("government", "index");
    }

    /**
     * Same government theme as {@code /tourism}, with generic department labelling.
     */
    @GetMapping("/government")
    String governmentPortal(Model model) {
        addGovernmentPortalModel(model, false);
        return CmsTemplateCatalog.themeView("government", "index");
    }

    /** Original heavy tourism snapshot (SharePoint-style markup) — kept for reference. */
    @GetMapping("/tourism/classic")
    String tourismClassicSnapshot(Model model) {
        String[] continents = DEMO_CONTINENTS;
        model.addAttribute("continents", continents);
        return CmsTemplateCatalog.themeView("site1", "index");
    }

    private static final String[] DEMO_CONTINENTS = {
        "Africa", "Antarctica", "Asia", "Australia",
        "Europe", "North America", "South America"
    };

    private void addGovernmentPortalModel(Model model, boolean tourismBranding) {
        Map<String, Object> params = new HashMap<>();
        params.put("theme", "government");
        if (tourismBranding) {
            params.put("pageTitle", "National Department of Tourism · Portal");
            params.put("departmentName", "National Department of Tourism");
            params.put("tagline", "Official portal layout — structured access to information, services, and sector programmes.");
            params.put("kicker", "Republic of South Africa · Tourism sector");
            params.put("heroTitle", "Welcome to the Department of Tourism");
            params.put("heroLead",
                "This page uses the new government theme: utility bar, crest masthead, banner, quick-access cards, and multi-column footer — "
                    + "in the same spirit as large departmental sites, without the legacy SharePoint HTML weight.");
            params.put("copyright", "© Demo layout only. Not the real tourism.gov.za website.");
        } else {
            params.put("pageTitle", "National government · Portal demo");
            params.put("departmentName", "National Department of Example Affairs");
            params.put("tagline", "Citizen-centred information and services — demo content.");
            params.put("kicker", "Republic · Official portal (demo)");
            params.put("heroTitle", "Welcome to our online services");
            params.put("heroLead",
                "Use this theme from the CMS by setting the site front-end theme to “government”. "
                    + "Styles live under static/government/css/; Thymeleaf under templates/themes/government/.");
            params.put("copyright", "© Demo government portal template.");
        }
        params.put("heroCtaText", "Quick access below");
        params.put("heroCtaHref", "#content");

        List<Map<String, String>> quickLinks = Arrays.asList(
            Map.of("title", "About the department", "desc", "Mandate, leadership, and organisational structure.", "href", "#"),
            Map.of("title", "Programmes & incentives", "desc", "Funds, grants, and sector support (demo).", "href", "#"),
            Map.of("title", "Publications", "desc", "Strategies, annual reports, and legislation.", "href", "#"),
            Map.of("title", "Contact & feedback", "desc", "Hotlines, offices, and service standards.", "href", "#")
        );
        params.put("quickLinks", quickLinks);

        model.addAttribute("parameters", params);
        model.addAttribute("continents", DEMO_CONTINENTS);
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

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Welcome to the Home Page!");
        return "index";
    }
}
