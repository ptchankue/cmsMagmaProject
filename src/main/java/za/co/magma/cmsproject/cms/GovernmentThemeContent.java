package za.co.magma.cmsproject.cms;

import java.util.List;

/**
 * Default HTML for {@link GovernmentSectionTitles} when seeding a new government site home page.
 */
public final class GovernmentThemeContent {

  public record SeededSection(String title, String html) {}

  public static List<SeededSection> defaultHomeSections() {
    return List.of(
        new SeededSection(
            GovernmentSectionTitles.QUICK_ACCESS,
            """
                <h3>Quick access</h3>
                <p class="gov-muted-text" style="margin-top:0;">
                    Common entry points styled like departmental portals. Edit this block under the home page → Sections (<code>gov_quick_access</code>).
                </p>
                <div class="gov-grid">
                    <a class="gov-card" href="#"><p class="gov-card-title">Online services</p><p class="gov-card-desc">Applications, status checks, and forms.</p></a>
                    <a class="gov-card" href="#"><p class="gov-card-title">Publications</p><p class="gov-card-desc">Policies, annual reports, and guides.</p></a>
                    <a class="gov-card" href="#"><p class="gov-card-title">News &amp; notices</p><p class="gov-card-desc">Press releases and public consultations.</p></a>
                    <a class="gov-card" href="#"><p class="gov-card-title">Contact</p><p class="gov-card-desc">Hotlines, regional offices, and feedback.</p></a>
                </div>"""),
        new SeededSection(
            GovernmentSectionTitles.GALLERY,
            """
                <h3>Gallery</h3>
                <p class="gov-muted-text" style="margin-top:0;">Replace with your own images or HTML. Section key: <code>gov_gallery</code>.</p>
                <div class="gov-grid" style="grid-template-columns:repeat(auto-fill,minmax(140px,1fr));gap:0.75rem;">
                    <div class="gov-panel" style="margin:0;min-height:100px;background:linear-gradient(135deg,#e8eef5,#d4dde8);"></div>
                    <div class="gov-panel" style="margin:0;min-height:100px;background:linear-gradient(135deg,#eef2e8,#dde5d4);"></div>
                    <div class="gov-panel" style="margin:0;min-height:100px;background:linear-gradient(135deg,#f5eef2,#e8d4dd);"></div>
                </div>"""),
        new SeededSection(
            GovernmentSectionTitles.FOOTER_COL1,
            """
                <h4>Department</h4>
                <ul>
                    <li><a href="#">About us</a></li>
                    <li><a href="#">Ministry</a></li>
                    <li><a href="#">Branches</a></li>
                    <li><a href="#">Careers</a></li>
                </ul>"""),
        new SeededSection(
            GovernmentSectionTitles.FOOTER_COL2,
            """
                <h4>Resources</h4>
                <ul>
                    <li><a href="#">Strategies &amp; plans</a></li>
                    <li><a href="#">Legislation</a></li>
                    <li><a href="#">Tenders</a></li>
                    <li><a href="#">PAIA</a></li>
                </ul>"""),
        new SeededSection(
            GovernmentSectionTitles.FOOTER_COL3,
            """
                <h4>Information</h4>
                <ul>
                    <li><a href="#">Government links</a></li>
                    <li><a href="#">Media releases</a></li>
                    <li><a href="#">Statistics</a></li>
                </ul>"""),
        new SeededSection(
            GovernmentSectionTitles.FOOTER_COL4,
            """
                <h4>Help</h4>
                <ul>
                    <li><a href="#">Contact centre</a></li>
                    <li><a href="#">Complaints</a></li>
                    <li><a href="#">Sitemap</a></li>
                    <li><a href="#">Disclaimer</a></li>
                </ul>"""));
  }

  private GovernmentThemeContent() {}
}
