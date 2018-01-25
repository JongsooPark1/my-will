package com.will.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);		// userId로도 조회할 수 있게 해준다
}
