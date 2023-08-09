package br.com.gusthavo.WorkshopMongo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gusthavo.WorkshopMongo.domain.Post;
import br.com.gusthavo.WorkshopMongo.domain.User;
import br.com.gusthavo.WorkshopMongo.dto.UserDTO;
import br.com.gusthavo.WorkshopMongo.mapper.Mapper;
import br.com.gusthavo.WorkshopMongo.resources.util.URL;
import br.com.gusthavo.WorkshopMongo.services.UserServices;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserServices services;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = services.findAll();
		var entity = Mapper.parseListObject(list, UserDTO.class);

		return ResponseEntity.ok().body(entity);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable(value = "id") String id) {
		User obj = services.findById(id);
		var entity = Mapper.parseObject(obj, UserDTO.class);

		return ResponseEntity.ok().body(entity);
	}

	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPosts(@PathVariable(value = "id") String id) {
		User obj = services.findById(id);

		return ResponseEntity.ok().body(obj.getPosts());
	}

	@PostMapping
	public ResponseEntity<UserDTO> insertUser(@RequestBody User user) {
		User obj = services.insertUser(user);
		var entity = Mapper.parseObject(obj, UserDTO.class);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		services.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO userdto, @PathVariable(value = "id") String id) {
		User obj = services.fromDTO(userdto);
		obj.setId(id);
		obj = services.update(obj);

		return ResponseEntity.noContent().build();
	}
	@GetMapping(value = "/namesearch")
	public ResponseEntity<List<User>> findByName(@RequestParam(value = "name", defaultValue = "")String name) {
		name = URL.decodeParamName(name);
		List<User> names = services.findByName(name);

		return ResponseEntity.ok().body(names);
	}
}
