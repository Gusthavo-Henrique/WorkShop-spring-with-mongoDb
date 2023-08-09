package br.com.gusthavo.WorkshopMongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gusthavo.WorkshopMongo.domain.Post;
import br.com.gusthavo.WorkshopMongo.repositories.PostRepository;
import br.com.gusthavo.WorkshopMongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostServices {

	@Autowired
	private PostRepository repository;

	
	public List<Post> findAll() {
		return repository.findAll();
	}
	
	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encotrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repository.findByTitleContaining(text);
	}
	
}
