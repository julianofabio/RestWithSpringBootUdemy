package br.com.erudio.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.ResourceClosedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.request.repository.PersonRepository;

///Notação services server para usar a classe sem instanciar
@Service
public class PersonServices {
	
	//private final AtomicLong counter = new AtomicLong();//auto incremento
	
	@Autowired
	PersonRepository repository;
	
	public Person findById (Long id) {
			//Person person = new Person();
			//person.setId(counter.incrementAndGet());
			//person.setFirstname("Juliano");
			//person.setLastname("Miranda");
			//person.setAddress("Barueri");
			//person.setGender("Masculino");
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	
	public List<Person> findByAll(){
		//List<Person> persons = new ArrayList<Person>();
		//for(int i = 0; i<8;i++) {
		//	Person person = mockPerson(i);
		//	persons.add(person);
		///}
		
		//return persons;
		
		return repository.findAll();
	}
	
	
	private Person mockPerson(int i) {
		Person person = new Person();
		//person.setId(counter.incrementAndGet());
		person.setFirstName("Person name" + i);
		person.setLastName("Last name" + i);
		person.setAddress("Some address in Brazil" + i);
		
		if (i % 3 == 0) 
			person.setGender("Male");
		else
			person.setGender("Female");
		return person;
	}
			
	
	public Person create (Person person) {
		//person.setId(counter.incrementAndGet());
		return repository.save(person);
	}
	
	public Person update (Person person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		//person.setId(counter.incrementAndGet());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	
	public void delete (Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
}
