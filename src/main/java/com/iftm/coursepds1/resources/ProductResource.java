package com.iftm.coursepds1.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iftm.coursepds1.dto.ProductDTO;
import com.iftm.coursepds1.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	@Autowired
	private ProductService service;
	
	@GetMapping // <=O metodo a baixo responde a uma requisiçao do tipo get
	public ResponseEntity<List<ProductDTO>> findAll(){   //metodo para devolver um usuario
		List<ProductDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
		
		
	}
	
	@GetMapping(value= "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id){    //@pathvariable esta pegando a estancia do id para passar para o long id
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
 
	
}
 