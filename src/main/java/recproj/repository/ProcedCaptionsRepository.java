package recproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recproj.entity.ProcedCaptions;

import java.util.List;

@Repository
public interface ProcedCaptionsRepository extends JpaRepository<ProcedCaptions, Integer> {

	List<ProcedCaptions> findAll();

	List<ProcedCaptions> findProcedCaptionsBycProc(int id);

	void deleteById(int id);
}
