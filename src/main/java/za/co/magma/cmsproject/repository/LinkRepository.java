package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {

  List<Link> findByPage(Page page);

  List<Link> findByPageIdIn(List<Long> ids);

  boolean existsByPage(Page page);

  List<Link> findBySiteOrderBySortOrderAscIdAsc(Site site);

  List<Link> findBySiteAndHeaderTrueAndEnabledTrueOrderBySortOrderAscIdAsc(Site site);

  List<Link> findBySiteAndFooterTrueAndEnabledTrueOrderBySortOrderAscIdAsc(Site site);

  Optional<Link> findFirstBySiteOrderBySortOrderDescIdDesc(Site site);
}
