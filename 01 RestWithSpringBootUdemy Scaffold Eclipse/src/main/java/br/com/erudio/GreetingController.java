package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter  = new AtomicLong();
	//Endereço que a API será acessada
	
	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value="name",defaultValue = "World")String name) {//parametro e valor default do parametro
		return new Greeting(String.format(template,name), counter.incrementAndGet()); 
		//o format substitui o %s pelo valor da variável name
		//a AtomicLong counter cria um número sequencial que é incrementado à cada chamada
	}
}
