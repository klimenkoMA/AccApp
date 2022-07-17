package recproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recproj.entity.AegerCaptions;
import recproj.repository.AegerCaptionsRepository;

import java.util.List;

@Service
public class AegerCaptionsService {
	@Autowired
	AegerCaptionsRepository aegerCaptionsRepository;

	public List<AegerCaptions> findAllAegerCaptions() {
		return aegerCaptionsRepository.findAll();
	}

	public void addNewAegerCaptions(AegerCaptions aegerCaptions) {
		aegerCaptionsRepository.save(aegerCaptions);
	}

	public void deleteAegerCaptionsById(int id) {
		aegerCaptionsRepository.deleteById(id);
	}

	public void updateAegerCaptions(AegerCaptions aegerCaptions) {
		aegerCaptionsRepository.save(aegerCaptions);
	}

	public List<AegerCaptions> getAegerCaptionsById(int id) {
		return aegerCaptionsRepository.findBycAeger(id);
	}
}
