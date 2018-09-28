package org.asalas.repo;

import org.asalas.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by donaldsmallidge on 3/7/17.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}