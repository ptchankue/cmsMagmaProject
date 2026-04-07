package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
  List<Page> findBySite(Site site);

  List<Page> findBySiteOrderByTitleAsc(Site site);

  List<Page> findBySiteOrderByIdAsc(Site site);

  Optional<Page> findFirstBySiteAndUrlIgnoreCase(Site site, String url);
}
