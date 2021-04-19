package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.Page;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
