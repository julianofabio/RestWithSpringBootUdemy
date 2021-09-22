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

import br.com.erudio.Greeting;
import br.com.erudio.math.SimpleMath;
import br.com.erudio.model.Person;
import br.com.erudio.model.services.PersonServices;

@RestController
@RequestMapping("/person")//Todos os métodos terão person na URL. Essa notação é opcional
public class PersonController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter  = new AtomicLong();
	
	@Autowired
	private SimpleMath calculatorMath;
	//Endereço que a API será acessada
	
	
	///////////////////////////////Greeting//////////////////////////////////////
	//http://localhost:8080/person/greeting?name=Udemy
	@RequestMapping("/greeting") //Endereço de acesso da do método
	 public Greeting greeting(@RequestParam(value="name",defaultValue = "World")String name) {//parametro e valor default do parametro. Parametros de entrada opcionais
		return new Greeting(String.format(template,name), counter.incrementAndGet()); 
		//o format substitui o %s pelo valor da variável name
		//a AtomicLong counter cria um número sequencial que é incrementado à cada chamada
	}
	
	
	//////////////////////////////CALCULADORA//////////////////////////////////////
	//Especificando o método GET como default. Se não especificar o default é o próprio método GET
	@RequestMapping(value="/sum/{numberOne}/{numberTwo}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double sum(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {

		return calculatorMath.sum(numberOne, numberTwo);
	}

	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double sub(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {

		return calculatorMath.sub(numberOne, numberTwo);
	}
	
	
	@RequestMapping(value="/mult/{numberOne}/{numberTwo}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double mult(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
		
		return calculatorMath.mult(numberOne, numberTwo);
	}
	
	
	@RequestMapping(value="/div/{numberOne}/{numberTwo}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double div(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
	
		return calculatorMath.div(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/med/{numberOne}/{numberTwo}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double med(@PathVariable("numberOne")String numberOne,@PathVariable("numberTwo")String numberTwo) throws Exception {
		
		return calculatorMath.med(numberOne, numberTwo);
	}
	
	@RequestMapping(value="/sqrt/{numberOne}",method=RequestMethod.GET) //Parametros de entrada obrigatórios
	public Double sqrt(@PathVariable("numberOne")String numberOne) throws Exception {
		
		return calculatorMath.sqrt(numberOne);
	}
	
	
	////////////////////////////PERSON//////////////////////////////
	
	//Notação que permite usar a classe PersonServices sem instanciar
	@Autowired
	private PersonServices services;
	
   /* ////IMPLEMENTAÇÃO DO MÉTODO GET (
	//http://localhost:8080/person/1
	//Pesquisa a pessoa pelo Id. Retornar o objeto Person
	@RequestMapping(value="/{id}",method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE) //Parametros de entrada obrigatórios
	public Person findById(@PathVariable("id") Long id){
		
		return services.findById(id);
	}
		
	
   ////IMPLEMENTAÇÃO DO MÉTODO GET
	//http://localhost:8080/person/
	@RequestMapping(method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE) 
	public List<Person> findAll(){
		
		return services.findByAll();
	}
	
	
    ////IMPLEMENTAÇÃO DO MÉTODO POST: (Usado apenas para cadastrar um novo objeto no banco)
	///Não dá pra chamar pelo Browner. É necessário criar o request pelo Postman ou chamando via código
	@RequestMapping(method=RequestMethod.POST, //Método POST 
					consumes= MediaType.APPLICATION_JSON_VALUE,//Consome/Recebe JSON
					produces= MediaType.APPLICATION_JSON_VALUE) //Produz/Retornar JSON
	public Person create(@RequestBody Person person){
		
		return services.create(person);
	}	
	
    ////IMPLEMENTAÇÃO DO MÉTODO pu: (Usado apenas para altera um objeto já existente no banco)
	///Não dá pra chamar pelo Browner. É necessário criar o request pelo Postman ou chamando via código
	@RequestMapping(method=RequestMethod.PUT,  //Método PUT
			consumes= MediaType.APPLICATION_JSON_VALUE,//Consome/Recebe JSON
			produces= MediaType.APPLICATION_JSON_VALUE) //Produz/Retornar JSON
	public Person update(@RequestBody Person person){
	
	return services.update(person);
	}
	
	
    ////IMPLEMENTAÇÃO DO MÉTODO DELETE: (Usado pra excluir objeto do banco)
	@RequestMapping(value="/{id}",
			method=RequestMethod.DELETE) //Não precisa consumir JSON porque iremos passar apenas o 
										//Id, mas poderia consumir se fosse passado o objeto inteiro
	public void delete(@PathVariable("id") Long id){
		services.delete(id);
	}
	*/
	
	
	 ////IMPLEMENTAÇÃO DO MÉTODO GET (
		//http://localhost:8080/person/1
	@GetMapping("/{id}") //Parametros de entrada obrigatórios
		public Person findById(@PathVariable("id") Long id){
			
			return services.findById(id);
		}
			
		
	   ////IMPLEMENTAÇÃO DO MÉTODO GET
		//http://localhost:8080/person/
		@GetMapping 
		public List<Person> findAll(){
			
			return services.findByAll();
		}
		
		
	    ////IMPLEMENTAÇÃO DO MÉTODO POST: (Usado apenas para cadastrar um novo objeto no banco)
		@PostMapping
		public Person create(@RequestBody Person person){
			
			return services.create(person);
		}	
		
	    ////IMPLEMENTAÇÃO DO MÉTODO pu: (Usado apenas para altera um objeto já existente no banco)
		@PutMapping
		public Person update(@RequestBody Person person){
		
		return services.update(person);
		}
		
		
	    ////IMPLEMENTAÇÃO DO MÉTODO DELETE: (Usado pra excluir objeto do banco)
		@DeleteMapping(value="/{id}") 
		public ResponseEntity<Person> delete(@PathVariable("id") Long id){
			services.delete(id);
			return ResponseEntity.ok().build();
		}
		
}
