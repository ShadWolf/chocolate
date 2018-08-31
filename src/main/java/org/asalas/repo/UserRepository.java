package org.asalas.repo;


import org.asalas.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tanert on 14.03.2018.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}