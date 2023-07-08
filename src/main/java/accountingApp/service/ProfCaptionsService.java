package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.ProfCaptions;
import accountingApp.repository.ProfCaptionsRepository;

import java.util.List;

@Service
public class ProfCaptionsService {
	@Autowired
	ProfCaptionsRepository profCaptionsRepository;

	public List<ProfCaptions> findAllProfCaptions() {
		return profCaptionsRepository.findAll();
	}

	public void addNewProfCaptions(ProfCaptions profCaptions) {
		profCaptionsRepository.save(profCaptions);
	}

	public void deleteProfCaptionsById(int id) {
		profCaptionsRepository.deleteById(id);
	}

	public void updateProfCaptions(ProfCaptions profCaptions) {
		profCaptionsRepository.save(profCaptions);
	}

	public List<ProfCaptions> getProfCaptionsById(int id) {
		return profCaptionsRepository.findBycProf(id);
	}
}
