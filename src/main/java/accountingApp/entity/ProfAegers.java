package accountingApp.entity;


import javax.persistence.*;

/**
 * Профессиональные заболевания
 */
@Entity
@Table(name = "prof_aegers")
public class ProfAegers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "c_prof")
	// ИНН профессии
	// int
	private ProfCaptions cProf;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "c_aeger")
	// ИНН заболевания
	// int
	private WorkArea cAeger;

	public ProfAegers(ProfCaptions cProf, WorkArea cAeger) {
		this.cProf = cProf;
		this.cAeger = cAeger;
	}

	public ProfAegers(int id, ProfCaptions cProf, WorkArea cAeger) {
		this.id = id;
		this.cProf = cProf;
		this.cAeger = cAeger;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProfCaptions getcProf() {
		return cProf;
	}

	public void setcProf(ProfCaptions cProf) {
		this.cProf = cProf;
	}

	public WorkArea getcAeger() {
		return cAeger;
	}

	public void setcAeger(WorkArea cAeger) {
		this.cAeger = cAeger;
	}

	public ProfAegers() {
	}


}

