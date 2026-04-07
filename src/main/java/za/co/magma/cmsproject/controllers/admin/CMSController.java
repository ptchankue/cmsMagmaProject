package za.co.magma.cmsproject.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.cms.CmsSiteViewParameters;
import za.co.magma.cmsproject.cms.CmsTemplateCatalog;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.repository.LinkRepository;
import za.co.magma.cmsproject.repository.PageRepository;
import za.co.magma.cmsproject.repository.SiteRepository;
import za.co.magma.cmsproject.utils.CMSUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("site")
public class CMSController {
  @Autowired
  private SiteRepository siteRepository;

  @Autowired
  private PageRepository pageRepository;

  @Autowired
  private LinkRepository linkRepository;

  private CMSUtils cmsUtils = new CMSUtils();

  @GetMapping("{id}/view")
  public String viewSiteGeneric(@PathVariable Long id,
                                Model model,
                                @RequestParam("page") Optional<Long> page) {
    Site site = siteRepository.findById(id).orElse(null);
    if (site == null) {
      return "admin/404";
    }

    Page p;
    if (page.isPresent()) {
      p = pageRepository.findById(page.get()).orElse(null);
    } else {
      p = pageRepository.findById(1L).orElse(null);
    }
    if (p == null) {
      return "admin/404";
    }

    try {
      cmsUtils.updateCSSColors(site.getColors());
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<Link> headerMenu = CmsSiteViewParameters.headerMenuForSite(pageRepository, linkRepository, site);
    Map<String, Object> parameters = CmsSiteViewParameters.forSitePageView(p, headerMenu);
    String theme = (String) parameters.get("theme");

    model.addAttribute("parameters", parameters);
    return CmsTemplateCatalog.themeView(theme, "page");
  }
}
