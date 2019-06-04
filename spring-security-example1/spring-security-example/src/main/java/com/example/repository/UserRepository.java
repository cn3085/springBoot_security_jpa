package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;

//JpaRepository를 상속받아 기본적인 CRUD 메소드를 받는다
public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
