package com.example.svmps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.svmps.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
