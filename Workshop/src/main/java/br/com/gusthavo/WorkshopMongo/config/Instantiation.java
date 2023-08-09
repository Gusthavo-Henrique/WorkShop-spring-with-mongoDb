package br.com.gusthavo.WorkshopMongo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.gusthavo.WorkshopMongo.domain.Post;
import br.com.gusthavo.WorkshopMongo.domain.User;
import br.com.gusthavo.WorkshopMongo.dto.AuthorDTO;
import br.com.gusthavo.WorkshopMongo.dto.ComentDTO;
import br.com.gusthavo.WorkshopMongo.repositories.PostRepository;
import br.com.gusthavo.WorkshopMongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	@Autowired
	PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, Instant.now(), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, Instant.now(), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

		ComentDTO c1 = new ComentDTO("Boa viagem mano!", Instant.now(), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveite!", Instant.now(), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("Tenha um ótimo dia!", Instant.now(), new AuthorDTO(alex));

		post1.getComent().addAll(Arrays.asList(c1,c2));
		post2.getComent().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
