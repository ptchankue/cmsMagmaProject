package za.co.magma.cmsproject.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.PageSection;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.domain.forms.LoginForm;
import za.co.magma.cmsproject.domain.forms.SectionForm;
import za.co.magma.cmsproject.domain.sections.SectionGeneric;
import za.co.magma.cmsproject.repository.*;
import za.co.magma.cmsproject.utils.CMSUtils;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("admin")
public class AdminController {
  @Autowired
  private BlogPostRepository blogPostRepository;

  @Autowired
  private SiteRepository siteRepository;

  @Autowired
  private PageRepository pageRepository;

  @Autowired
  private PageSectionRepository pageSectionRepository;

  @Autowired
  private LinkRepository linkRepository;

  private LoginForm loginForm;
  private Map<String, Object> params;
  private CMSUtils cmsUtils = new CMSUtils();
  private static Logger logger = Logger.getLogger(AdminController.class);

  private Map<String, Object> setGlobalVariables() {
    params = new HashMap<>();

    params.put("messages", "9");
    params.put("notifications", "4");
    params.put("pageTitle", "");
    params.put("name", "Valerie Luna");

    params.put("theme", "cms1");
    params.put("phone", "+27 11 222 3333");
    params.put("email", "contact@industryalinc.com");

    params.put("sites", siteRepository.findAll());

    return params;
  }

  @RequestMapping("/home")
  public String home(Model model) {
    logger.info("adminHome");

    Map<String, Map<String, String>> map = new HashMap<>();
    Map<String, String> w3cc = new HashMap<>();
    params = setGlobalVariables();
    w3cc.put("title", "My Car");

    map.put("w3css", w3cc);

    params.put("pageTitle", "MyCMS - Dashboard");
    model.addAttribute("parameters", params);
    model.addAttribute("sites", siteRepository.findAll());

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
          password.equalsIgnoreCase("admin")) {
        success = true;
      }
    }
    params.put("pageTitle", "MyCMS - Login");
    model.addAttribute("loginForm", loginForm);
    model.addAttribute("success", success);
    if (success) {
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
   * Just to understand how to make this generic enough
   *
   * @param model
   * @return
   */
  @GetMapping("/test/site")
  public String viewSite(Model model) {
    params = setGlobalVariables();

    Site site = siteRepository.findById(1L).orElse(null);
    System.out.println(">> SITE NAME: " + site!=null?site.getName():"No site loaded");
    if(null!=site){
      // Check if all needed variables exists
    }
    params.put("pageTitle", "CMS Admin Pages");
    params.put("theme", "cms1");
    params.put("slider", "true");

    model.addAttribute("parameters", params);
    return params.get("theme") + "/index";
  }

  @GetMapping("/site")
  public String viewADMINSite(@RequestParam("id") long id,  Model model) {
    logger.info("viewADMINSite id=" + id);
    params = setGlobalVariables();

    Site site = siteRepository.findById(id).orElse(null);
    System.out.println(">> SITE NAME: " + site!=null?site.getName():"No site loaded");
    if(null!=site){
      // Check if all needed variables exists
      List<Page> pageList = pageRepository.findBySite(site);
      params.put("pages", pageList);
      params.put("site", site);

    }
    params.put("pageTitle", "CMS Admin Pages");
    params.put("theme", "cms1");
    params.put("slider", "true");

    model.addAttribute("parameters", params);
    return "admin/admin_pages";
  }

  @GetMapping("/edit/page")
  public String viewADMINEditPage(@RequestParam("id") long id,  Model model) {
    logger.info("viewADMINEditPage id=" + id);
    params = setGlobalVariables();

    Page page = pageRepository.findById(id).orElse(null);
    if(null!=page){
      // Check if all needed variables exists
      params.put("page", page);
      // GEt sections
      List<PageSection> pageSectionList = pageSectionRepository.findByPage(page);
      params.put("sections", pageSectionList);

    }
    params.put("pageTitle", "CMS Admin Edit Page");

    model.addAttribute("parameters", params);
    return "admin/admin_edit_page";
  }

  @GetMapping("/edit/section")
  public String viewADMINEditSection(@RequestParam("id") long id,
                                     @RequestParam(value = "template", required = false) String template, Model model) {
    logger.info("viewADMINEditSection id=" + id);
    params = setGlobalVariables();

    PageSection pageSection = pageSectionRepository.findById(id).orElse(null);
    if(null!=pageSection){
      // Check if all needed variables exists
      params.put("section", pageSection);
      params.put("template", template);
//      logger.info(pageSection);

      List<SectionForm> sectionFormList = new ArrayList<>();
      sectionFormList.add(new SectionForm("Online", "checkbox", ""));

      Map<String, Object> vars = new HashMap<>();
      vars.put("title", "");

      if(id == 7){
        SectionGeneric sectionGeneric = new SectionGeneric(pageSection);
        String theme="top_area{imgTop:/cms1/img/page-top-bg/1.jpg<>textTitle:About us<>textBody:Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque orci purus, sodales in est quis, blandit sollicitudin est. Nam ornare ipsum ac accumsan auctor.<>textHello:Contact us}";
        params.put("formFields", sectionGeneric.getFields());
        params.put("theme", theme);
      }

      model.addAttribute("section", pageSection);
      model.addAttribute("formFields", vars);
      model.addAttribute("variables", vars);

    }
    params.put("pageTitle", "CMS Admin Edit Section");

    model.addAttribute("parameters", params);
    return "admin/admin_edit_section";
  }

  @PostMapping("/edit/section")
  public String postADMINEditSection(@ModelAttribute @Valid PageSection section,Model model) {
    logger.info("postADMINEditSection: " + section);
    params = setGlobalVariables();
    params.put("section", section);

    PageSection pageSection = pageSectionRepository.findById(section.getId()).orElse(null);
    if(null!=pageSection){
      // Check if all needed variables exists
      if(!section.equals(pageSection)){
        section.setUpdated(new Date());

        System.out.println("initial object has changed, update here");
      }
    }

    params.put("pageTitle", "CMS Admin Edit Section");
    params.put("template", section.getTemplate());

    model.addAttribute("parameters", params);
    model.addAttribute("sectionForm", section);

    return "redirect:/admin/edit/section?id="+section.getId()+"&template="+section.getTemplate();

//    return "admin/admin_edit_section";
  }


}
