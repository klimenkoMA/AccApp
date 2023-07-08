package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.ProfAegers;
import accountingApp.repository.ProfAegersRepository;

import java.util.List;

@Service
public class ProfAegersService {
	@Autowired
	ProfAegersRepository profAegersRepository;

	public List<ProfAegers> findAllProfAegers() {
		return profAegersRepository.findAll();
	}

	public void addNewProfAegers(ProfAegers profAegers) {
		profAegersRepository.save(profAegers);
	}

	public void deleteProfAegersById(int id) {
		profAegersRepository.deleteById(id);
	}

	public void updateProfAegers(ProfAegers profAegers) {
		profAegersRepository.save(profAegers);
	}

	public List<ProfAegers> getProfAegerssById(int id) {
		return profAegersRepository.findById(id);
	}
}
