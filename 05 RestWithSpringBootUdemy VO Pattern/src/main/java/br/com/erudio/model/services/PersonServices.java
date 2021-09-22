package br.com.erudio.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.converter.custom.PersonConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.request.repository.PersonRepository;

///Notação services server para usar a classe sem instanciar
@Service
public class PersonServices {
	
	//private final AtomicLong counter = new AtomicLong();//auto incremento
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;
	
	public PersonVO findById (Long id) {
		
		Person entity= repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(entity,PersonVO.class);
	}
	
	
	public List<PersonVO> findByAll(){

		return DozerConverter.parseListObjects(repository.findAll(),PersonVO.class);
	}
	
	
	private PersonVO mockPersonVO(int i) {
		PersonVO person = new PersonVO();
		person.setFirstName("PersonVO name" + i);
		person.setLastName("Last name" + i);
		person.setAddress("Some address in Brazil" + i);
		
		if (i % 3 == 0) 
			person.setGender("Male");
		else
			person.setGender("Female");
		return person;
	}
			
	
	public PersonVO create (PersonVO person) {
		//Convertendo um PersonVO em um Person
		Person entity = DozerConverter.parseObject(person, Person.class);
		entity = repository.save(entity);//Salva no banco
		
		//Convertendo um Person em um PersonVO
		PersonVO vo = DozerConverter.parseObject(entity, PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2 (PersonVOV2 person) {
		//Convertendo um PersonVO em um Person
		Person entity = converter.convertVOToEntity(person);
		entity = repository.save(entity);//Salva no banco
		
		//Convertendo um Person em um PersonVO
		PersonVOV2 vo = converter.convertEntityToVO(entity);
		return vo;
	}
	
	public PersonVO update (PersonVO person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		//person.setId(counter.incrementAndGet());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerConverter.parseObject(repository.save(entity), PersonVO.class);
	}
	
	
	public void delete (Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
	
}
