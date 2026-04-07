package za.co.magma.cmsproject.cms;

import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.repository.LinkRepository;
import za.co.magma.cmsproject.repository.PageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shared {@code parameters} map for site front-end views (cms1, government, minimal, …).
 */
public final class CmsSiteViewParameters {

  private CmsSiteViewParameters() {}

  public static List<Link> headerMenuForSite(PageRepository pageRepository, LinkRepository linkRepository, Site site) {
    if (site == null) {
      return List.of();
    }
    List<Link> menu = new ArrayList<>();
    for (Page p : pageRepository.findBySite(site)) {
      Link link = linkRepository.findByPage(p);
      if (link != null && link.isHeader()) {
        menu.add(link);
      }
    }
    return menu;
  }

  public static Map<String, Object> forSitePageView(Page page, List<Link> headerMenu) {
    Site site = page.getSite();
    Map<String, Object> params = new HashMap<>();
    String theme = CmsTemplateCatalog.normalizeSiteTheme(site != null ? site.getTemplate() : null);
    params.put("theme", theme);
    params.put("site", site);
    params.put("page", page);
    params.put("body", page.getBody());
    params.put("pageTitle", page.getTitle());
    params.put("phone", site != null ? site.getPhone() : null);
    params.put("email", site != null ? site.getEmail() : null);
    params.put("slider", "true");
    params.put("headerMenu", headerMenu != null ? headerMenu : List.of());
    params.put("footerMenu", List.of());
    if (site != null) {
      params.put("departmentName", site.getName());
    }
    return params;
  }
}
