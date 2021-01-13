package com.iftm.coursepds1.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iftm.coursepds1.dto.UserDTO;
import com.iftm.coursepds1.dto.UserInsertDTO;
import com.iftm.coursepds1.entities.User;
import com.iftm.coursepds1.repositories.UserRepository;
import com.iftm.coursepds1.services.exception.DatabaseException;
import com.iftm.coursepds1.services.exception.ResourceNotFoundException;

@Service                        // fazer o registro da classe service no spring 
public class UserService {     	// classe ira buscar usarios por id e busca por todos usuarios
    
	@Autowired
	private UserRepository repository;							// fazer injeção de dependencia com userRepository
	
	
	public List<UserDTO> findAll(){
	  List<User> list  = repository.findAll();
	  return list.stream().map(e ->new UserDTO(e)).collect(Collectors.toList());
	}
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
	}
		
	public UserDTO insert(UserInsertDTO dto) {     //salvar o usuario
		User entity = dto.toEntity();
		entity = repository.save(entity);
		return new UserDTO(entity);
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
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			User entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new UserDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	private void updateData(User entity, UserDTO dto) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
	}
}
	
