package za.co.magma.cmsproject.controllers.admin;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.PageSection;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.cms.CmsTemplateCatalog;
import za.co.magma.cmsproject.domain.forms.LoginForm;
import za.co.magma.cmsproject.domain.forms.SectionForm;
import za.co.magma.cmsproject.domain.sections.SectionGeneric;
import za.co.magma.cmsproject.repository.*;
import za.co.magma.cmsproject.utils.CMSUtils;

import jakarta.validation.Valid;
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

  @RequestMapping({"/home", ""})
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
    model.addAttribute("siteCount", siteRepository.count());
    model.addAttribute("pageCount", pageRepository.count());

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
    return CmsTemplateCatalog.themeView((String) params.get("theme"), "index");
  }

  @GetMapping("/sites")
  public String listSites(Model model) {
    params = setGlobalVariables();
    params.put("pageTitle", "Sites");
    model.addAttribute("parameters", params);
    return "admin/admin_sites";
  }

  @GetMapping("/site/new")
  public String newSiteForm(Model model) {
    params = setGlobalVariables();
    params.put("pageTitle", "New site");
    model.addAttribute("parameters", params);
    model.addAttribute("siteEntity", new Site());
    model.addAttribute("siteThemes", CmsTemplateCatalog.siteThemes());
    return "admin/admin_edit_site";
  }

  @GetMapping("/site/edit")
  public String editSiteForm(@RequestParam("id") long id, Model model, RedirectAttributes ra) {
    params = setGlobalVariables();
    Site site = siteRepository.findById(id).orElse(null);
    if (site == null) {
      ra.addFlashAttribute("error", "Site not found");
      return "redirect:/admin/sites";
    }
    site.setTemplate(CmsTemplateCatalog.normalizeSiteTheme(site.getTemplate()));
    params.put("pageTitle", "Edit site");
    model.addAttribute("parameters", params);
    model.addAttribute("siteEntity", site);
    model.addAttribute("siteThemes", CmsTemplateCatalog.siteThemes());
    return "admin/admin_edit_site";
  }

  @PostMapping("/site/save")
  public String saveSite(@ModelAttribute Site siteEntity,
                         @RequestParam(value = "seedDefaultPages", required = false) String seedDefaultPages,
                         RedirectAttributes ra) {
    Long incomingId = siteEntity.getId();
    if (siteEntity.getName() == null || siteEntity.getName().isBlank()) {
      ra.addFlashAttribute("error", "Site name is required");
      if (incomingId != null && incomingId > 0) {
        return "redirect:/admin/site/edit?id=" + incomingId;
      }
      return "redirect:/admin/site/new";
    }
    boolean updateExisting =
        incomingId != null && incomingId > 0 && siteRepository.existsById(incomingId);
    if (updateExisting) {
      Site existing = siteRepository.findById(incomingId).get();
      existing.setName(siteEntity.getName().trim());
      existing.setPhone(siteEntity.getPhone());
      existing.setEmail(siteEntity.getEmail());
      existing.setHost(siteEntity.getHost());
      existing.setLanguage(siteEntity.getLanguage());
      existing.setTemplate(CmsTemplateCatalog.normalizeSiteTheme(siteEntity.getTemplate()));
      siteRepository.save(existing);
      ra.addFlashAttribute("message", "Site updated");
      return "redirect:/admin/site/edit?id=" + existing.getId();
    }
    Site created = new Site();
    created.setId(null);
    created.setName(siteEntity.getName().trim());
    created.setPhone(siteEntity.getPhone());
    created.setEmail(siteEntity.getEmail());
    created.setHost(siteEntity.getHost());
    created.setLanguage(siteEntity.getLanguage() != null && !siteEntity.getLanguage().isBlank() ? siteEntity.getLanguage() : "en");
    created.setTemplate(CmsTemplateCatalog.normalizeSiteTheme(siteEntity.getTemplate()));
    siteRepository.save(created);
    if (isTruthyFormFlag(seedDefaultPages)) {
      seedDefaultPagesForNewSite(created);
      ra.addFlashAttribute("message", "Site created with Home, Contact, and About starter pages (where URLs were free)");
    } else {
      ra.addFlashAttribute("message", "Site created");
    }
    return "redirect:/admin/site/edit?id=" + created.getId();
  }

  private static boolean isTruthyFormFlag(String value) {
    return "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value) || "1".equals(value);
  }

  private void seedDefaultPagesForNewSite(Site site) {
    for (String key : new String[] {"home", "contact", "about"}) {
      final String starterKey = key;
      CmsTemplateCatalog.findPageStarter(starterKey).ifPresent(starter -> {
        if (pageRepository.findFirstBySiteAndUrlIgnoreCase(site, starter.getUrlSlug()).isPresent()) {
          return;
        }
        Page p = new Page();
        p.setSite(site);
        p.setOnline(true);
        CmsTemplateCatalog.applyPageStarter(p, starterKey, site.getName());
        pageRepository.save(p);
      });
    }
  }

  @GetMapping("/site")
  public String viewADMINSite(@RequestParam("id") long id, Model model, RedirectAttributes ra) {
    logger.info("viewADMINSite id=" + id);
    params = setGlobalVariables();

    Site site = siteRepository.findById(id).orElse(null);
    if (site == null) {
      ra.addFlashAttribute("error", "Site not found");
      return "redirect:/admin/sites";
    }
    List<Page> pageList = pageRepository.findBySiteOrderByTitleAsc(site);
    params.put("pages", pageList);
    params.put("site", site);
    params.put("pageTitle", "Pages · " + site.getName());
    params.put("theme", "cms1");
    params.put("slider", "true");

    model.addAttribute("parameters", params);
    model.addAttribute("siteThemes", CmsTemplateCatalog.siteThemes());
    return "admin/admin_pages";
  }

  @PostMapping("/site/theme")
  public String updateSiteTheme(@RequestParam("id") long siteId,
                                @RequestParam("template") String template,
                                RedirectAttributes ra) {
    Site site = siteRepository.findById(siteId).orElse(null);
    if (site == null) {
      ra.addFlashAttribute("error", "Site not found");
      return "redirect:/admin/sites";
    }
    site.setTemplate(CmsTemplateCatalog.normalizeSiteTheme(template));
    siteRepository.save(site);
    ra.addFlashAttribute("message", "Front-end theme updated to \"" + site.getTemplate() + "\"");
    return "redirect:/admin/site?id=" + siteId;
  }

  @GetMapping("/edit/page")
  public String viewADMINEditPage(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "siteId", required = false) Long siteId,
                                  @RequestParam(value = "starter", required = false) String starter,
                                  Model model,
                                  RedirectAttributes ra) {
    params = setGlobalVariables();
    Page cmsPage;
    List<PageSection> sections;

    if (id != null) {
      cmsPage = pageRepository.findById(id).orElse(null);
      if (cmsPage == null) {
        ra.addFlashAttribute("error", "Page not found");
        return "redirect:/admin/sites";
      }
      sections = pageSectionRepository.findByPageOrderByPositionAscIdAsc(cmsPage);
      params.put("pageTitle", "Edit page · " + cmsPage.getTitle());
    } else {
      if (siteId == null) {
        ra.addFlashAttribute("error", "Choose a site before creating a page");
        return "redirect:/admin/sites";
      }
      Site site = siteRepository.findById(siteId).orElse(null);
      if (site == null) {
        ra.addFlashAttribute("error", "Site not found");
        return "redirect:/admin/sites";
      }
      cmsPage = new Page();
      cmsPage.setSite(site);
      cmsPage.setOnline(true);
      if (starter != null && !starter.isBlank()) {
        CmsTemplateCatalog.applyPageStarter(cmsPage, starter, site.getName());
        model.addAttribute("appliedStarter", starter.trim().toLowerCase(Locale.ROOT));
      }
      sections = Collections.emptyList();
      params.put("pageTitle", "New page · " + site.getName());
    }

    params.put("page", cmsPage);
    params.put("sections", sections);
    model.addAttribute("parameters", params);
    model.addAttribute("cmsPage", cmsPage);
    model.addAttribute("pageStarters", CmsTemplateCatalog.pageStartersForPicker());
    return "admin/admin_edit_page";
  }

  @PostMapping("/edit/page")
  public String savePage(@ModelAttribute("cmsPage") Page form,
                         @RequestParam(value = "siteId", required = false) Long siteId,
                         RedirectAttributes ra) {
    if (form.getTitle() == null || form.getTitle().isBlank()) {
      ra.addFlashAttribute("error", "Title is required");
      return redirectAfterPageError(form, siteId);
    }
    if (form.getUrl() == null || form.getUrl().isBlank()) {
      ra.addFlashAttribute("error", "URL slug is required (e.g. home, contact)");
      return redirectAfterPageError(form, siteId);
    }

    String slug = form.getUrl().trim().replaceAll("\\s+", "-");

    if (form.getId() != null && pageRepository.existsById(form.getId())) {
      Page persisted = pageRepository.findById(form.getId()).get();
      Optional<Page> urlTaken =
          pageRepository.findFirstBySiteAndUrlIgnoreCase(persisted.getSite(), slug);
      if (urlTaken.isPresent() && !urlTaken.get().getId().equals(persisted.getId())) {
        ra.addFlashAttribute("error", "Another page in this site already uses that URL");
        return "redirect:/admin/edit/page?id=" + persisted.getId();
      }
      persisted.setTitle(form.getTitle().trim());
      persisted.setBody(form.getBody());
      persisted.setUrl(slug);
      persisted.setOnline(form.isOnline());
      pageRepository.save(persisted);
      ra.addFlashAttribute("message", "Page saved");
      return "redirect:/admin/edit/page?id=" + persisted.getId();
    }

    Site site = resolveSiteForNewPage(form, siteId);
    if (site == null) {
      ra.addFlashAttribute("error", "Site is required");
      return "redirect:/admin/sites";
    }

    Optional<Page> duplicate = pageRepository.findFirstBySiteAndUrlIgnoreCase(site, slug);
    if (duplicate.isPresent()) {
      ra.addFlashAttribute("error", "A page with this URL already exists for this site");
      return "redirect:/admin/edit/page?siteId=" + site.getId();
    }

    Page created = new Page();
    created.setSite(site);
    created.setTitle(form.getTitle().trim());
    created.setBody(form.getBody());
    created.setUrl(slug);
    created.setOnline(form.isOnline());
    pageRepository.save(created);
    ra.addFlashAttribute("message", "Page created");
    return "redirect:/admin/edit/page?id=" + created.getId();
  }

  private String redirectAfterPageError(Page form, Long siteId) {
    if (form.getId() != null) {
      return "redirect:/admin/edit/page?id=" + form.getId();
    }
    if (siteId != null) {
      return "redirect:/admin/edit/page?siteId=" + siteId;
    }
    if (form.getSite() != null && form.getSite().getId() != null) {
      return "redirect:/admin/edit/page?siteId=" + form.getSite().getId();
    }
    return "redirect:/admin/sites";
  }

  private Site resolveSiteForNewPage(Page form, Long siteId) {
    if (form.getSite() != null && form.getSite().getId() != null) {
      return siteRepository.findById(form.getSite().getId()).orElse(null);
    }
    if (siteId != null) {
      return siteRepository.findById(siteId).orElse(null);
    }
    return null;
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
