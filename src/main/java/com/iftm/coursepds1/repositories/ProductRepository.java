package com.iftm.coursepds1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iftm.coursepds1.entities.Product;

public interface ProductRepository extends JpaRepository <Product, Long>{

}
