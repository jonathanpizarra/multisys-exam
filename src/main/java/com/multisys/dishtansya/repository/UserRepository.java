package com.multisys.dishtansya.repository;

import com.multisys.dishtansya.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM user_account u WHERE u.email = :email", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);

    User findByEmail(String email);

}
