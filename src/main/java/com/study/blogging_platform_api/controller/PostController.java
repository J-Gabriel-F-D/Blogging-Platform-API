package com.study.blogging_platform_api.controller;

import com.study.blogging_platform_api.model.Post;
import com.study.blogging_platform_api.repository.PostRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostRepository repository;

	public PostController(PostRepository repository){
		this.repository = repository;
	}

	@GetMapping
	public List<Post> findAllPost (){
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Post findPostById (@PathVariable Long id){
		return repository.findById(id).orElse(null);
	}

	@PostMapping	
	public Post createPost (@RequestBody Post post){
		return repository.save(post);
	}

	@PutMapping("/{id}")
	public Post updatePost (@PathVariable Long id, @RequestBody Post post){

		Post existente = repository.findById(id).orElse(null);

		if (existente == null) {
			return null;
		}

		existente.setTitle(post.getTitle());
		existente.setContent(post.getContent());

		return repository.save(existente);
	}

	@DeleteMapping("/{id}")
	public void deletePost (@PathVariable Long id){
		repository.deleteById(id);
	}

}