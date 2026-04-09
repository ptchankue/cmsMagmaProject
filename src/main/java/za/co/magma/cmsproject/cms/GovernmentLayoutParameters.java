package za.co.magma.cmsproject.cms;

import za.co.magma.cmsproject.domain.PageSection;

import java.util.List;
import java.util.Map;

/** Maps home-page {@link PageSection} rows into {@code parameters} for government templates. */
public final class GovernmentLayoutParameters {

  private GovernmentLayoutParameters() {}

  public static void applyHomeSectionParams(Map<String, Object> params, List<PageSection> homeSections) {
    if (homeSections == null || params == null) {
      return;
    }
    for (PageSection s : homeSections) {
      if (!s.isOnline()) {
        continue;
      }
      String t = s.getTitle();
      if (t == null) {
        continue;
      }
      String html = s.getHtml();
      if (html == null) {
        html = "";
      }
      switch (t) {
        case GovernmentSectionTitles.QUICK_ACCESS -> params.put("gov_quick_access", html);
        case GovernmentSectionTitles.GALLERY -> params.put("gov_gallery", html);
        case GovernmentSectionTitles.FOOTER_COL1 -> params.put("gov_footer_col1", html);
        case GovernmentSectionTitles.FOOTER_COL2 -> params.put("gov_footer_col2", html);
        case GovernmentSectionTitles.FOOTER_COL3 -> params.put("gov_footer_col3", html);
        case GovernmentSectionTitles.FOOTER_COL4 -> params.put("gov_footer_col4", html);
        default -> { }
      }
    }
  }
}
