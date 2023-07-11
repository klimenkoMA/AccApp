package accountingApp.entity;

import javax.persistence.*;

/**
 * Диагноз
 */
@Entity
@Table(name = "recreants_aegers")
public class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JoinColumn(name = "waypaper_snn")
	//код путевки int

	@ManyToOne
	@JoinColumn(name = "c_aeger")
	//ИНН заболевания int
	private WorkArea cAeger;

	public Events(int id, WorkArea cAeger) {
		this.id = id;
		this.cAeger = cAeger;
	}

	public Events() {
	}

	public Events(WorkArea cAeger) {
		this.cAeger = cAeger;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}




	public WorkArea getcAeger() {
		return cAeger;
	}

	public void setcAeger(WorkArea cAeger) {
		this.cAeger = cAeger;
	}
}
