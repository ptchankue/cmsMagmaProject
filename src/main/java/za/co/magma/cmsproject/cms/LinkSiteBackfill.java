package za.co.magma.cmsproject.cms;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.repository.LinkRepository;

/**
 * Copies {@link za.co.magma.cmsproject.domain.Site} from each link's page onto the link row so
 * site-scoped menu queries work for data created before {@code site_id} existed.
 */
@Component
@Order(100)
public class LinkSiteBackfill implements ApplicationRunner {

  private final LinkRepository linkRepository;

  public LinkSiteBackfill(LinkRepository linkRepository) {
    this.linkRepository = linkRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    for (Link link : linkRepository.findAll()) {
      if (link.getSite() == null && link.getPage() != null && link.getPage().getSite() != null) {
        link.setSite(link.getPage().getSite());
        linkRepository.save(link);
      }
    }
  }
}
