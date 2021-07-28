package com.hardik.cognus.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.cognus.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
