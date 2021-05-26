package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Link;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.Site;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
  Link findByPage(Page page);
  List<Link> findByPageIdIn(List<Long> ids);
}
