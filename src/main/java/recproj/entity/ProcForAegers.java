package recproj.entity;

import javax.persistence.*;

/**
 * Процедуры для заболеваний
 */
@Entity
@Table(name = "proc_for_aegers")
public class ProcForAegers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "c_aeger")
	//ИНН заболевания
	private AegerCaptions aegerCaptions;

	@ManyToOne
	@JoinColumn(name = "c_proced")
	// ИНН процедуры
	private ProcedCaptions procedCaptions;

	public ProcForAegers(int id, AegerCaptions aegerCaptions, ProcedCaptions procedCaptions) {
		this.id = id;
		this.aegerCaptions = aegerCaptions;
		this.procedCaptions = procedCaptions;
	}

	public ProcForAegers() {

	}

	public ProcForAegers(AegerCaptions aegerCaptions, ProcedCaptions procedCaptions) {
		this.aegerCaptions = aegerCaptions;
		this.procedCaptions = procedCaptions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AegerCaptions getAegerCaptions() {
		return aegerCaptions;
	}

	public void setAegerCaptions(AegerCaptions aegerCaptions) {
		this.aegerCaptions = aegerCaptions;
	}

	public ProcedCaptions getProcedCaptions() {
		return procedCaptions;
	}

	public void setProcedCaptions(ProcedCaptions procedCaptions) {
		this.procedCaptions = procedCaptions;
	}
}
