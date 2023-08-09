package br.com.gusthavo.WorkshopMongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.gusthavo.WorkshopMongo.domain.User;
import java.util.List;


public interface UserRepository extends MongoRepository<User, String>{
	public List<User> findByName(String name);
}
