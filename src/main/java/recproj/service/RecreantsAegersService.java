package recproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recproj.entity.RecreantsAegers;
import recproj.repository.RecreantsAegersRepository;

import java.util.List;

@Service
public class RecreantsAegersService {
	@Autowired
	RecreantsAegersRepository recreantsAegersRepository;

	public List<RecreantsAegers> findAllRecreantsAegers() {
		return recreantsAegersRepository.findAll();
	}

	public void addNewRecreantsAegers(RecreantsAegers recreantsAegers) {
		recreantsAegersRepository.save(recreantsAegers);
	}

	public void deleteRecreantsAegersById(int id) {
		recreantsAegersRepository.deleteById(id);
	}

	public void updateRecreantsAegers(RecreantsAegers recreantsAegers) {
		recreantsAegersRepository.save(recreantsAegers);
	}

	public List<RecreantsAegers> getRecreantsAegersById(int id) {
		return recreantsAegersRepository.findById(id);
	}
}
