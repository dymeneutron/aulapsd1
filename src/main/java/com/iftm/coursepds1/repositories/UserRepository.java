package com.iftm.coursepds1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iftm.coursepds1.entities.User;

public interface UserRepository extends JpaRepository <User, Long>{

}
