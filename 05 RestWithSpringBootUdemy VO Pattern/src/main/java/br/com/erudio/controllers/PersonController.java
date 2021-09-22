package br.com.erudio.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.math.SimpleMath;
import br.com.erudio.model.services.PersonServices;

@RestController
@RequestMapping("/person")//Todos os métodos terão person na URL. Essa notação é opcional
public class PersonController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter  = new AtomicLong();

	
	////////////////////////////PERSON//////////////////////////////
	
	//Notação que permite usar a classe PersonVOServices sem instanciar
	@Autowired
	private PersonServices services;
	

	
	 ////IMPLEMENTAÇÃO DO MÉTODO GET (
		//http://localhost:8080/person/1
	@GetMapping("/{id}") //Parametros de entrada obrigatórios
		public PersonVO findById(@PathVariable("id") Long id){
			
			return services.findById(id);
		}
			
		
	   ////IMPLEMENTAÇÃO DO MÉTODO GET
		//http://localhost:8080/person/
		@GetMapping 
		public List<PersonVO> findAll(){
			
			return services.findByAll();
		}
		
		
	    ////IMPLEMENTAÇÃO DO MÉTODO POST: (Usado apenas para cadastrar um novo objeto no banco)
		@PostMapping
		public PersonVO create(@RequestBody PersonVO person){
			
			return services.create(person);
		}	
		
	    ////IMPLEMENTAÇÃO DO MÉTODO POST: (Usado apenas para cadastrar um novo objeto no banco)
		@PostMapping("/v2")
		public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
			
			return services.createV2(person);
		}	
		
	    ////IMPLEMENTAÇÃO DO MÉTODO pu: (Usado apenas para altera um objeto já existente no banco)
		@PutMapping
		public PersonVO update(@RequestBody PersonVO person){
		
		return services.update(person);
		}
		
		
	    ////IMPLEMENTAÇÃO DO MÉTODO DELETE: (Usado pra excluir objeto do banco)
		@DeleteMapping(value="/{id}") 
		public ResponseEntity<PersonVO> delete(@PathVariable("id") Long id){
			services.delete(id);
			return ResponseEntity.ok().build();
		}
		
}
