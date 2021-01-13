package com.iftm.coursepds1.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iftm.coursepds1.dto.CategoryDTO;
import com.iftm.coursepds1.dto.ProductCategoriesDTO;
import com.iftm.coursepds1.dto.ProductDTO;
import com.iftm.coursepds1.entities.Category;
import com.iftm.coursepds1.entities.Product;
import com.iftm.coursepds1.repositories.CategoryRepository;
import com.iftm.coursepds1.repositories.ProductRepository;
import com.iftm.coursepds1.services.exception.DatabaseException;
import com.iftm.coursepds1.services.exception.ResourceNotFoundException;

@Service                        // fazer o registro da classe service no spring 
public class ProductService {     	// classe ira buscar usarios por id e busca por todos usuarios
    
	@Autowired
	private ProductRepository repository;							// fazer injeção de dependencia com userRepository
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Page<ProductDTO> findAllPaged(Pageable pageable) {
		Page<Product> list =  repository.findAll(pageable);
		return list.map(e -> new ProductDTO(e));
	}
	public ProductDTO  findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
	
	@Transactional                 // transactional diz que o comandos tem que executar nesse momento
	public ProductDTO insert(ProductCategoriesDTO dto) {     //salvar o usuario
		Product entity = dto.toEntity();
		setProductCategories(entity,dto.getCategories());
		entity = repository.save(entity);
		return new ProductDTO(entity);
	}

	
	@Transactional
	public ProductDTO update(Long id, ProductCategoriesDTO dto) {
		try {
			Product entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public void delete(Long id) {
		try{ 
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {   // retorna uma exeção em caso de não haver usuarios para deletar
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
			}
		} 
	
	private void updateData(Product entity, ProductCategoriesDTO dto) {
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		if(dto.getCategories() != null && dto.getCategories().size() > 0) {
			setProductCategories(entity,dto.getCategories());
		}
	}
	
	private void setProductCategories(Product entity, List<CategoryDTO> categories) {
		entity.getCategories().clear();
		for(CategoryDTO dto : categories) {
			Category category = categoryRepository.getOne(dto.getId());
			entity.getCategories().add(category);
		}
	}
}
