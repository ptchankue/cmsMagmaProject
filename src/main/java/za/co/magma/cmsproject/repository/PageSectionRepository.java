package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Page;
import za.co.magma.cmsproject.domain.PageSection;

import java.util.List;

@Repository
public interface PageSectionRepository extends JpaRepository<PageSection, Long> {
  List<PageSection> findByPage(Page page);
}
