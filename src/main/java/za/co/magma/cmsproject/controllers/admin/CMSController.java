package za.co.magma.cmsproject.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.domain.forms.LoginForm;
import za.co.magma.cmsproject.repository.BlogPostRepository;
import za.co.magma.cmsproject.repository.LinkRepository;
import za.co.magma.cmsproject.repository.PageRepository;
import za.co.magma.cmsproject.repository.SiteRepository;
import za.co.magma.cmsproject.utils.CMSUtils;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("site")
public class CMSController {
  @Autowired
  private BlogPostRepository blogPostRepository;

  @Autowired
  private SiteRepository siteRepository;

  @Autowired
  private PageRepository pageRepository;

  @Autowired
  private LinkRepository linkRepository;

  private Map<String, Object> params;
  private CMSUtils cmsUtils = new CMSUtils();

  private Map<String, Object> setGlobalVariables() {
    params = new HashMap<>();

    params.put("messages", "9");
    params.put("notifications", "4");
    params.put("pageTitle", "");
    params.put("name", "Valerie Luna");
    params.put("theme", "cms1");

    return params;
  }

  @GetMapping("{id}/view")
  public String viewSiteGeneric(@PathVariable Long id,
                                Model model,
                                @RequestParam("page") Optional<Long> page) {
    params = setGlobalVariables();

    Site site = siteRepository.findById(id).orElse(null);
    if (null==site) {
      // 404
      return "admin/404";
    }
    params.put("site", site);
    params.put("theme", site.getTemplate());
    params.put("slider", "true");

    params.put("phone", site.getPhone());
    params.put("email", site.getEmail());

    List<Page> pageList = pageRepository.findBySite(site);
    System.out.println("Site: "+site.getName() +". Pages count: " + pageList.size());

    List<Link> headerMenu = getHeaderMenu(pageList);
    params.put("headerMenu", headerMenu);

    if (page.isPresent()) {
      Page p = pageRepository.findById(page.get())
        .orElse(null);
      params.put("body", p.getBody());
      params.put("page", p);
    } else {
      // Show home page
      Page p = pageRepository.findById(1L).orElse(null);
      System.out.println(p.getId() + " - " + p.getTitle());
      params.put("body", p.getBody());
      params.put("page", p);
    }

    // Get menus

    model.addAttribute("parameters", params);
    return params.get("theme") + "/page";
  }

  private List<Link> getHeaderMenu(List<Page> pageList) {
    List<Link> headerMenu = new ArrayList<>();
    for(Page p: pageList){
      Link link = linkRepository.findByPage(p);
      if(null != link && link.isHeader()){
        headerMenu.add(link);
        System.out.println("\t"+link.getTitle()+", page="+link.getPage().getId());
      }
    }
    return headerMenu;
  }
}
