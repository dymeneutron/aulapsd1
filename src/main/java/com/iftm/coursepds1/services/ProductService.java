package com.iftm.coursepds1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.coursepds1.dto.ProductDTO;
import com.iftm.coursepds1.entities.Product;
import com.iftm.coursepds1.repositories.ProductRepository;
import com.iftm.coursepds1.services.exception.ResourceNotFoundException;

@Service                        // fazer o registro da classe service no spring 
public class ProductService {     	// classe ira buscar usarios por id e busca por todos usuarios
    
	@Autowired
	private ProductRepository repository;							// fazer injeção de dependencia com userRepository
	
	
	public List<ProductDTO> findAll() {
		List<Product> list =  repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	public ProductDTO  findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
		
	
}
