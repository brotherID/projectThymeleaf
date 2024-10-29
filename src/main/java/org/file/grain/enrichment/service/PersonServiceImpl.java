package org.file.grain.enrichment.service;

import javax.persistence.EntityNotFoundException;

import org.file.grain.enrichment.annotation.CustomAnnotation;
import org.file.grain.enrichment.model.Person;
import org.file.grain.enrichment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person addPersonne(Person person) {

		return personRepository.save(person);
	}

	@CustomAnnotation
	@Override
	public Person findPersonById(Long idPerson) {

		return personRepository.findById(idPerson)
				.orElseThrow(() -> new EntityNotFoundException("Person not found with id: " + idPerson));

	}

	@Override
	public Page<Person> findPersons(int page, int size) {
		return personRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deletePerson(Long idPerson) {
		personRepository.deleteById(idPerson);
	}

	@Override
	public Page<Person> findByFirstNameContainsIgnoreCase(String keyword, int page, int size) {
		return personRepository.findByFirstNameContainsIgnoreCase(keyword, PageRequest.of(page, size));
	}

}
