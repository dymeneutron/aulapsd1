package com.iftm.coursepds1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iftm.coursepds1.dto.OrderDTO;
import com.iftm.coursepds1.entities.Order;
import com.iftm.coursepds1.repositories.OrderRepository;
import com.iftm.coursepds1.services.exception.ResourceNotFoundException;

@Service                        // fazer o registro da classe service no spring 
public class OrderService {     	// classe ira buscar usarios por id e busca por todos usuarios
    
	@Autowired
	private OrderRepository repository;							// fazer injeção de dependencia com userRepository
	
	public List<OrderDTO> findAll(){
		List<Order> list = repository.findAll();
		return list.stream().map(e -> new OrderDTO(e)).collect(Collectors.toList());
	}
	public OrderDTO findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		Order entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new OrderDTO(entity);
	}
		
	
}
