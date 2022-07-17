package recproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recproj.entity.Persons;
import recproj.repository.PersonsRepository;

import java.util.List;

@Service
public class PersonsService {

	@Autowired
	private PersonsRepository personsRepository;

	public void deletePersonsById(int id) {
		personsRepository.deleteById(id);
	}

	public List<Persons> getListPersons() {
		return personsRepository.findAll();
	}

	public void updatePerson(Persons persons) {
		personsRepository.save(persons);
	}

	public List<Persons> findPersonsByFio(String fio) {
		return personsRepository.findByFio(fio);
	}

	public List<Persons> findPersonsById(int id) {
		return personsRepository.findById(id);
	}
}
