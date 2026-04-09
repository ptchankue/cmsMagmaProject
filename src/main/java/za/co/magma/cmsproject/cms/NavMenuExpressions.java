package za.co.magma.cmsproject.cms;

import za.co.magma.cmsproject.domain.Link;

/**
 * Static helpers for Thymeleaf / SpringEL (SpEL often blocks instance methods like {@code link.targetsPage(...)} on entities).
 */
public final class NavMenuExpressions {

    private NavMenuExpressions() {}

    public static boolean linkTargetsPage(Link link, Long currentPageId) {
        if (link == null) {
            return false;
        }
        return link.targetsPage(currentPageId);
    }
}
