package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Site;

import java.util.List;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {
  List<Site> findByName(String name);
}
