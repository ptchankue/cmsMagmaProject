package za.co.magma.cmsproject.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import za.co.magma.cmsproject.cms.CmsSiteViewParameters;
import za.co.magma.cmsproject.cms.CmsTemplateCatalog;
import za.co.magma.cmsproject.cms.GovernmentLayoutParameters;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.PageSection;
import za.co.magma.cmsproject.domain.Site;
import za.co.magma.cmsproject.repository.LinkRepository;
import za.co.magma.cmsproject.repository.PageRepository;
import za.co.magma.cmsproject.repository.PageSectionRepository;
import za.co.magma.cmsproject.repository.SiteRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class PageController {

    private final PageRepository pageRepository;
    private final SiteRepository siteRepository;
    private final LinkRepository linkRepository;
    private final PageSectionRepository pageSectionRepository;

    public PageController(PageRepository pageRepository, SiteRepository siteRepository, LinkRepository linkRepository,
                          PageSectionRepository pageSectionRepository) {
        this.pageRepository = pageRepository;
        this.siteRepository = siteRepository;
        this.linkRepository = linkRepository;
        this.pageSectionRepository = pageSectionRepository;
    }

    @GetMapping("/view/{pageId}")
    public String viewPage(@PathVariable Long pageId, Model model) {
        Page page = pageRepository.findById(pageId).orElse(null);
        if (page == null) {
            return "admin/404";
        }
        Site site = page.getSite();
        List<Link> headerMenu = CmsSiteViewParameters.headerMenuForSite(pageRepository, linkRepository, site);
        List<Link> footerMenu = CmsSiteViewParameters.footerMenuForSite(pageRepository, linkRepository, site);
        List<PageSection> pageSections = pageSectionRepository.findByPageOrderByPositionAscIdAsc(page);
        Map<String, Object> parameters = CmsSiteViewParameters.forSitePageView(page, headerMenu, footerMenu, pageSections);
        parameters.put("navUseViewPath", Boolean.TRUE);
        String theme = (String) parameters.get("theme");
        if ("government".equals(theme) && site != null) {
            pageRepository.findFirstBySiteAndUrlIgnoreCase(site, "home").ifPresent(home ->
                GovernmentLayoutParameters.applyHomeSectionParams(
                    parameters, pageSectionRepository.findByPageOrderByPositionAscIdAsc(home)));
        }

        model.addAttribute("parameters", parameters);
        model.addAttribute("page", page);
        model.addAttribute("site", site);
        return CmsTemplateCatalog.themeView(theme, "page");
    }

    @RequestMapping("/public/site/{siteId}")
    public String publicSitePages(@PathVariable Long siteId, Model model) {
        Site site = siteRepository.findById(siteId).orElse(null);
        if (site == null) {
            return "admin/404";
        }
        List<Page> pages = pageRepository.findBySiteOrderByTitleAsc(site);
        model.addAttribute("site", site);
        model.addAttribute("pages", pages);
        Optional<Page> home = pageRepository.findFirstBySiteAndUrlIgnoreCase(site, "home");
        model.addAttribute("homePage", home.orElse(pages.isEmpty() ? null : pages.get(0)));
        return "public/site";
    }
}
