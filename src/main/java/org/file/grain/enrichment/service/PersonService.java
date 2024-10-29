package org.file.grain.enrichment.service;

import org.file.grain.enrichment.model.Person;
import org.springframework.data.domain.Page;

public interface PersonService {

	Person addPersonne(Person person);

	Person findPersonById(Long idPerson);

	Page<Person> findPersons(int page, int size);

	void deletePerson(Long idPerson);

	Page<Person> findByFirstNameContainsIgnoreCase(String keyword, int page,int size);

}
