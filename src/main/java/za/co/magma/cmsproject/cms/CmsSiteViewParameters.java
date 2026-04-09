package za.co.magma.cmsproject.cms;

import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.PageSection;
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
    List<Link> bySite = linkRepository.findBySiteAndHeaderTrueAndEnabledTrueOrderBySortOrderAscIdAsc(site);
    if (!bySite.isEmpty()) {
      return bySite;
    }
    return legacyMenuForSite(pageRepository, linkRepository, site, true);
  }

  public static List<Link> footerMenuForSite(PageRepository pageRepository, LinkRepository linkRepository, Site site) {
    if (site == null) {
      return List.of();
    }
    List<Link> bySite = linkRepository.findBySiteAndFooterTrueAndEnabledTrueOrderBySortOrderAscIdAsc(site);
    if (!bySite.isEmpty()) {
      return bySite;
    }
    return legacyMenuForSite(pageRepository, linkRepository, site, false);
  }

  private static List<Link> legacyMenuForSite(PageRepository pageRepository, LinkRepository linkRepository, Site site,
                                              boolean header) {
    List<Link> menu = new ArrayList<>();
    for (Page p : pageRepository.findBySiteOrderByTitleAsc(site)) {
      for (Link link : linkRepository.findByPage(p)) {
        if (header && link.isHeader() && link.isEnabled()) {
          menu.add(link);
        } else if (!header && link.isFooter() && link.isEnabled()) {
          menu.add(link);
        }
      }
    }
    return menu;
  }

  public static Map<String, Object> forSitePageView(Page page, List<Link> headerMenu, List<Link> footerMenu,
                                                     List<PageSection> pageSections) {
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
    params.put("footerMenu", footerMenu != null ? footerMenu : List.of());
    params.put("sections", pageSections != null ? pageSections : List.of());
    params.put("navUseViewPath", Boolean.FALSE);
    if (site != null) {
      params.put("departmentName", site.getName());
    }
    return params;
  }
}
