package br.com.gusthavo.WorkshopMongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gusthavo.WorkshopMongo.domain.Post;
import br.com.gusthavo.WorkshopMongo.resources.util.URL;
import br.com.gusthavo.WorkshopMongo.services.PostServices;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
	@Autowired
	private PostServices services;

	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> obj = services.findAll();
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable(value = "id") String id) {
		Post obj = services.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping(value = "/titlesearch" )
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "")String text ) {
		text = URL.decodeParam(text);
		List<Post> list = services.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
}
