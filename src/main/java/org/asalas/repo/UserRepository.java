package org.asalas.repo;

import org.asalas.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by donaldsmallidge on 3/7/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.encryptedPassword = :newpas WHERE u.id = :id")
    public void modEncPass(@Param("id") Integer id, @Param("newpas") String newpas);

}