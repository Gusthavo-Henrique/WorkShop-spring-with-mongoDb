package br.com.gusthavo.WorkshopMongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gusthavo.WorkshopMongo.domain.User;
import br.com.gusthavo.WorkshopMongo.dto.UserDTO;
import br.com.gusthavo.WorkshopMongo.repositories.UserRepository;
import br.com.gusthavo.WorkshopMongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {

		return repository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado"));
	}

	public User insertUser(User user) {
		User obj = repository.insert(user);

		return obj;
	}

	public User fromDTO(UserDTO userDto) {

		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
	
	public void delete(String id) {
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
		}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	public List<User> findByName(String name) {
		return repository.findByName(name);
	}
}
