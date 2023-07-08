package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.ProcForAegers;
import accountingApp.repository.ProcForAegersRepository;

import java.util.List;

@Service
public class ProcForAegersService {
	@Autowired
	ProcForAegersRepository procForAegersRepository;

	public List<ProcForAegers> findAllProcForAegers() {
		return procForAegersRepository.findAll();
	}

	public void addNewProcForAegers(ProcForAegers procForAegers) {
		procForAegersRepository.save(procForAegers);
	}

	public void deleteProcForAegersById(int id) {
		procForAegersRepository.deleteById(id);
	}

	public void updateProcForAegers(ProcForAegers procForAegers) {
		procForAegersRepository.save(procForAegers);
	}

	public List<ProcForAegers> getProcForAegersById(int id) {
		return procForAegersRepository.findById(id);
	}
}
