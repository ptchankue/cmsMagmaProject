package za.co.magma.cmsproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.magma.cmsproject.domain.walrus.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
}
