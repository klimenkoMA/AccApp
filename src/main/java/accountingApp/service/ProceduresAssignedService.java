package accountingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import accountingApp.entity.ProceduresAssigned;
import accountingApp.repository.ProceduresAssignedRepository;

import java.util.List;

@Service
public class ProceduresAssignedService {
	@Autowired
	ProceduresAssignedRepository proceduresAssignedRepository;

	public List<ProceduresAssigned> findAllProceduresAssigned() {
		return proceduresAssignedRepository.findAll();
	}

	public void addNewProceduresAssigned(ProceduresAssigned proceduresAssigned) {
		proceduresAssignedRepository.save(proceduresAssigned);
	}

	public void deleteProceduresAssignedById(int id) {
		proceduresAssignedRepository.deleteById(id);
	}

	public void updateProceduresAssigned(ProceduresAssigned proceduresAssigned) {
		proceduresAssignedRepository.save(proceduresAssigned);
	}

	public List<ProceduresAssigned> getProceduresAssignedById(int id) {
		return proceduresAssignedRepository.findByid(id);
	}
}
