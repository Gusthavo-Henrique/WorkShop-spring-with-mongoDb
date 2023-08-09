package br.com.gusthavo.WorkshopMongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gusthavo.WorkshopMongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	List<Post> findByTitleContaining(String text);
}
