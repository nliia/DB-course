package com.pinum.dbcourse.repository;

import com.pinum.dbcourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lnurullina
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
