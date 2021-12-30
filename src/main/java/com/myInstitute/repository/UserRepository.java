package com.myInstitute.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myInstitute.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
