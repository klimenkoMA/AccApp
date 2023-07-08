package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.Recreants;
import accountingApp.repository.RecreantsRepository;

import java.util.List;

@Service
public class RecreantsService {
	@Autowired
	RecreantsRepository recreantsRepository;

	public List<Recreants> findAllRecreants() {
		return recreantsRepository.findAll();
	}

	public void addNewARecreants(Recreants recreants) {
		recreantsRepository.save(recreants);
	}

	public void deleteRecreantsById(int id) {
		recreantsRepository.deleteById(id);
	}

	public void updateRecreants(Recreants recreants) {
		recreantsRepository.save(recreants);
	}

	public List<Recreants> getRecreantsById(int id) {
		return recreantsRepository.findById(id);
	}
}
