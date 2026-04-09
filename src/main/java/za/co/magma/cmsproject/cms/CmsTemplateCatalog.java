package za.co.magma.cmsproject.cms;

import za.co.magma.cmsproject.domain.Page;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Declares selectable front-end themes for sites and starter content for new pages.
 */
public final class CmsTemplateCatalog {

  /** Thymeleaf classpath prefix for site themes (under {@code templates/}). */
  public static final String THEMES_PREFIX = "themes/";

  private CmsTemplateCatalog() {}

  /**
   * Resolves a view name inside a theme folder, e.g. {@code themes/cms1/index}.
   */
  public static String themeView(String themeId, String viewName) {
    String theme = (themeId == null || themeId.isBlank()) ? "cms1" : themeId.trim();
    return THEMES_PREFIX + theme + "/" + viewName;
  }

  public static final class SiteTheme {
    private final String id;
    private final String label;
    private final String description;

    public SiteTheme(String id, String label, String description) {
      this.id = id;
      this.label = label;
      this.description = description;
    }

    public String getId() {
      return id;
    }

    public String getLabel() {
      return label;
    }

    public String getDescription() {
      return description;
    }
  }

  public static final class PageStarter {
    private final String key;
    private final String label;
    private final String description;
    private final String title;
    private final String urlSlug;
    private final String bodyTemplate;

    public PageStarter(String key, String label, String description,
                       String title, String urlSlug, String bodyTemplate) {
      this.key = key;
      this.label = label;
      this.description = description;
      this.title = title;
      this.urlSlug = urlSlug;
      this.bodyTemplate = bodyTemplate;
    }

    public String getKey() {
      return key;
    }

    public String getLabel() {
      return label;
    }

    public String getDescription() {
      return description;
    }

    public String getTitle() {
      return title;
    }

    public String getUrlSlug() {
      return urlSlug;
    }
  }

  private static final List<SiteTheme> SITE_THEMES = List.of(
      new SiteTheme(
          "cms1",
          "CMS1 — Industry & services",
          "Matches templates under themes/cms1/ (hero, services blocks, contact). Best default for this project."),
      new SiteTheme(
          "site1",
          "Site1 — Tourism sample",
          "Heavy sample layout under themes/site1/. Uses static assets under /site1/."),
      new SiteTheme(
          "government",
          "Government — Departmental portal",
          "Structured portal (masthead, banner, panels, footer) with CSS under static/government/. Similar layout cues to /tourism."),
      new SiteTheme(
          "minimal",
          "Minimal — Content only",
          "Body text only: no cms1 header, slider, or footer. Use for embeds or the simplest pages. Not the industry layout — choose CMS1 for that.")
  );

  private static final Map<String, PageStarter> PAGE_STARTERS;

  static {
    Map<String, PageStarter> m = new LinkedHashMap<>();
    m.put("blank", new PageStarter(
        "blank",
        "Blank page",
        "Empty title and body — fill everything yourself.",
        "",
        "",
        ""
    ));
    m.put("home", new PageStarter(
        "home",
        "Home",
        "Welcome hero, short intro, call-to-action.",
        "Home",
        "home",
        "<section class=\"mb-4\">"
            + "<p class=\"lead\">Welcome to <strong>%s</strong>.</p>"
            + "<p>This is your home page. Replace this text with your own message, or switch to another starter before saving.</p>"
            + "<p><a class=\"btn btn-primary btn-sm\" href=\"#\">Primary action</a></p>"
            + "</section>"
    ));
    m.put("contact", new PageStarter(
        "contact",
        "Contact",
        "Heading plus contact placeholders.",
        "Contact",
        "contact",
        "<h2>Contact us</h2>"
            + "<p>Reach <strong>%s</strong> using the details below.</p>"
            + "<ul>"
            + "<li>Email: <em>you@example.com</em></li>"
            + "<li>Phone: <em>+00 000 000 0000</em></li>"
            + "<li>Address: <em>Your street, City</em></li>"
            + "</ul>"
    ));
    m.put("about", new PageStarter(
        "about",
        "About",
        "Simple about layout with mission block.",
        "About us",
        "about",
        "<h2>About %s</h2>"
            + "<p>Share your story, mission, and what makes your organisation different.</p>"
            + "<blockquote class=\"border-left pl-3 text-muted\">"
            + "Replace this quote with a short mission statement."
            + "</blockquote>"
    ));
    m.put("services", new PageStarter(
        "services",
        "Services",
        "Three-column style service list.",
        "Services",
        "services",
        "<h2>What we offer</h2>"
            + "<p><strong>%s</strong> provides the following services:</p>"
            + "<div class=\"row\">"
            + "<div class=\"col-md-4\"><h4>Service one</h4><p>Short description.</p></div>"
            + "<div class=\"col-md-4\"><h4>Service two</h4><p>Short description.</p></div>"
            + "<div class=\"col-md-4\"><h4>Service three</h4><p>Short description.</p></div>"
            + "</div>"
    ));
    m.put("publications", new PageStarter(
        "publications",
        "Publications",
        "Policies, reports, and documents placeholder.",
        "Publications",
        "publications",
        "<h2>Publications</h2>"
            + "<p>Listing for <strong>%s</strong>: add PDFs, annual reports, and policy documents here.</p>"
            + "<ul><li>Annual report (draft)</li><li>Strategic plan (draft)</li></ul>"
    ));
    m.put("news", new PageStarter(
        "news",
        "News",
        "News and notices starter.",
        "News and notices",
        "news",
        "<h2>News and notices</h2>"
            + "<p>Latest updates for <strong>%s</strong>.</p>"
            + "<article class=\"mb-3\"><h3>Sample headline</h3><p>Replace with real releases or consultations.</p></article>"
    ));
    PAGE_STARTERS = Collections.unmodifiableMap(m);
  }

  public static List<SiteTheme> siteThemes() {
    return SITE_THEMES;
  }

  public static List<PageStarter> pageStartersForPicker() {
    return PAGE_STARTERS.values().stream()
        .filter(p -> !"blank".equals(p.getKey()))
        .collect(Collectors.toList());
  }

  public static Optional<PageStarter> findPageStarter(String key) {
    if (key == null || key.isBlank()) {
      return Optional.empty();
    }
    return Optional.ofNullable(PAGE_STARTERS.get(key.trim().toLowerCase(Locale.ROOT)));
  }

  public static void applyPageStarter(Page page, String starterKey, String siteDisplayName) {
    findPageStarter(starterKey).ifPresent(starter -> apply(page, starter, siteDisplayName));
  }

  private static void apply(Page page, PageStarter starter, String siteDisplayName) {
    if ("blank".equals(starter.getKey())) {
      page.setTitle("");
      page.setUrl("");
      page.setBody("");
      return;
    }
    String name = siteDisplayName != null && !siteDisplayName.isBlank() ? siteDisplayName : "your site";
    page.setTitle(starter.getTitle());
    page.setUrl(starter.getUrlSlug());
    if (starter.bodyTemplate == null || starter.bodyTemplate.isEmpty()) {
      page.setBody("");
    } else {
      page.setBody(String.format(starter.bodyTemplate, name));
    }
  }

  public static String normalizeSiteTheme(String templateId) {
    if (templateId == null || templateId.isBlank()) {
      return "cms1";
    }
    String t = templateId.trim();
    if ("default".equalsIgnoreCase(t)) {
      return "minimal";
    }
    for (SiteTheme theme : SITE_THEMES) {
      if (theme.getId().equalsIgnoreCase(t)) {
        return theme.getId();
      }
    }
    return "cms1";
  }
}
