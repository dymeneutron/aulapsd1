package com.iftm.coursepds1.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iftm.coursepds1.dto.OrderDTO;
import com.iftm.coursepds1.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	@Autowired
	private OrderService service;
	
	@GetMapping // <=O metodo a baixo responde a uma requisiçao do tipo get
	public ResponseEntity<List<OrderDTO>> findAll(){   //metodo para devolver um usuario
		List<OrderDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
		
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id){    //@pathvariable esta pegando a estancia do id para passar para o long id
		OrderDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
 
	
}
 