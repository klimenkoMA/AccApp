package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.ProcedCaptions;
import accountingApp.repository.ProcedCaptionsRepository;

import java.util.List;

@Service
public class ProcedCaptionsService {
	@Autowired
	ProcedCaptionsRepository procedCaptionsRepository;

	public List<ProcedCaptions> findAllProcedCaptions() {
		return procedCaptionsRepository.findAll();
	}

	public void addNewProcedCaptions(ProcedCaptions procedCaptions) {
		procedCaptionsRepository.save(procedCaptions);
	}

	public void deleteProcedCaptionsById(int id) {
		procedCaptionsRepository.deleteById(id);
	}

	public void updateProcedCaptions(ProcedCaptions procedCaptions) {
		procedCaptionsRepository.save(procedCaptions);
	}

	public List<ProcedCaptions> getProcedCaptionsById(int id) {
		return procedCaptionsRepository.findProcedCaptionsBycProc(id);
	}
}
