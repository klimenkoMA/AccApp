package accountingApp.service;

import accountingApp.entity.Repair;
import accountingApp.repository.RepairRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    private final Logger logger = LoggerFactory.getLogger(RepairService.class);

    @Autowired
    private RepairRepository repairRepository;

    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    public Repair createRepair(Repair repair) {
        logger.warn("Repair with device " + repair.getDevice().getName() + " was created!");
        return repairRepository.save(repair);
    }

    public Repair updateRepair(Repair repair) {
        logger.warn("Repair with device " + repair.getDevice().getName() + " was updated!");
        return repairRepository.save(repair);
    }

    public List<Repair> findRepair(long id) {
        return repairRepository.findRepairsById(id);
    }

    public void deleteRepair(long id) {
        Repair repair = repairRepository.findRepairsById(id).get(0);
        logger.warn("Repair with device " + repair.getDevice().getName() + " was deleted!");
        repairRepository.deleteById(id);
    }

}
