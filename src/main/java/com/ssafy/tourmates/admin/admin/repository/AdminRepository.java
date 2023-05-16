package com.ssafy.tourmates.admin.admin.repository;

import com.ssafy.tourmates.admin.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByLoginIdAndLoginPw(@Param("loginId") String loginId, @Param("loginPw") String loginPw);

    Optional<Admin> findByLoginId(@Param("loginId") String loginId);
}
