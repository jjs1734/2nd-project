package com.wizian.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wizian.web.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 추가적인 메서드가 필요하다면 여기에 작성할 수 있습니다.
}
