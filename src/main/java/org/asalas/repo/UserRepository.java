package org.asalas.repo;

import org.asalas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by donaldsmallidge on 3/7/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}