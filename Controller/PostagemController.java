package br.org.generation.blogpessoal.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoal.model.postagem;
import br.org.generation.blogpessoal.repository.postagemrepository;


@RestController
@RequestMapping("/postagens") 
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class PostagemController {
	
	@Autowired 
	private postagemrepository postagemrepository;
	

	@GetMapping
	public ResponseEntity<List<postagem>> getAll (){
		return ResponseEntity.ok(postagemrepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<postagem> getById(@PathVariable long id) {
		return postagemrepository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemrepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<postagem> postPostagem (@Valid @RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemrepository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<postagem> putPostagem (@Valid @RequestBody postagem postagem){
		
		return postagemrepository.findById(postagem.getId())
			.map(resposta -> ResponseEntity.ok().body(postagemrepository.save(postagem)))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return postagemrepository.findById(id)
				.map(resposta -> {
					postagemrepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}

