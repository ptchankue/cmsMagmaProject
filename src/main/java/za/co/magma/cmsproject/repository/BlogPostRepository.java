package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.magma.cmsproject.domain.BlogPost;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
//  List<BlogPost> findByPerson_Id(Long id);
}
