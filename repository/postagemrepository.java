package br.org.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.blogpessoal.model.postagem;

@Repository
public interface postagemrepository extends JpaRepository<postagem, Long> {

	public List <postagem> findAllByTituloContainingIgnoreCase(String titulo); 
		
}