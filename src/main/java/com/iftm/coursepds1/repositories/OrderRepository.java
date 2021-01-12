package com.iftm.coursepds1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iftm.coursepds1.entities.Order;

public interface OrderRepository extends JpaRepository <Order, Long>{

}
