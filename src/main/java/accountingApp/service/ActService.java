package accountingApp.service;

import accountingApp.entity.Act;
import accountingApp.repository.ActRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActService {

    @Autowired
    private ActRepository actRepository;

    public List<Act> getAllActs() {
        return actRepository.findAll();
    }

    public Act getActById(Long id) {
        return actRepository.findById(id).orElse(null);
    }

    public Act saveAct(Act act) {
        return actRepository.save(act);
    }

    public void deleteAct(Long id) {
        actRepository.deleteById(id);
    }
}