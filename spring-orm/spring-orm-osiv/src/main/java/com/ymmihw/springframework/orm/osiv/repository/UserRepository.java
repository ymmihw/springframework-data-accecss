package com.ymmihw.springframework.orm.osiv.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ymmihw.springframework.orm.osiv.model.BasicUser;

public interface UserRepository extends JpaRepository<BasicUser, Long> {

  @EntityGraph(attributePaths = "permissions")
  Optional<BasicUser> findDetailedByUsername(String username);

  Optional<BasicUser> findSummaryByUsername(String username);

  Optional<BasicUser> findByUsername(String username);
}
