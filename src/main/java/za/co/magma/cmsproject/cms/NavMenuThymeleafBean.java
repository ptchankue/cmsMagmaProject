package za.co.magma.cmsproject.cms;

import org.springframework.stereotype.Component;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Site;

import java.util.Map;

/**
 * Exposed to Thymeleaf as {@code @navMenu} because SpEL often disallows instance methods on entities
 * and may restrict {@code T(...)} static calls in hardened setups.
 */
@Component("navMenu")
public class NavMenuThymeleafBean {

    public boolean linkTargetsPage(Link link, Long currentPageId) {
        return NavMenuExpressions.linkTargetsPage(link, currentPageId);
    }

    /** Resolves {@code activeNavPageId} from the site parameters map (preferred in templates). */
    public boolean linkTargetsPage(Link link, Map<?, ?> parameters) {
        return linkTargetsPage(link, activeNavPageId(parameters));
    }

    /** Reads {@code navUseViewPath} from the site view parameters map without fragile SpEL on the map. */
    public boolean navUsesViewPath(Map<?, ?> parameters) {
        if (parameters == null) {
            return false;
        }
        return Boolean.TRUE.equals(parameters.get("navUseViewPath"));
    }

    public Long siteId(Map<?, ?> parameters) {
        if (parameters == null) {
            return null;
        }
        Object s = parameters.get("site");
        if (s instanceof Site) {
            return ((Site) s).getId();
        }
        return null;
    }

    public Long activeNavPageId(Map<?, ?> parameters) {
        if (parameters == null) {
            return null;
        }
        Object id = parameters.get("activeNavPageId");
        if (id instanceof Long) {
            return (Long) id;
        }
        if (id instanceof Number) {
            return ((Number) id).longValue();
        }
        return null;
    }

    /** Null-safe; avoids SpEL {@code navItem.page} (null link or lazy/proxy edge cases). */
    public boolean linkHasPage(Link link) {
        return link != null && link.getPage() != null;
    }

    public Long linkPageId(Link link) {
        return linkHasPage(link) ? link.getPage().getId() : null;
    }

    public boolean linkWithoutPage(Link link) {
        return link != null && link.getPage() == null;
    }

    /** Internal page → {@code /view/{id}} when {@code navUseViewPath} and a site are in the model. */
    public boolean site1UseViewLink(Link link, Map<?, ?> parameters) {
        if (!linkHasPage(link) || parameters == null || parameters.get("site") == null) {
            return false;
        }
        return Boolean.TRUE.equals(parameters.get("navUseViewPath"));
    }

    /** Internal page → site-scoped view URL. */
    public boolean site1UseSiteScopedLink(Link link, Map<?, ?> parameters) {
        if (!linkHasPage(link) || parameters == null || parameters.get("site") == null) {
            return false;
        }
        return !Boolean.TRUE.equals(parameters.get("navUseViewPath"));
    }
}
