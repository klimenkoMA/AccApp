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

	@ManyToOne
	@JoinColumn(name = "waypaper_snn")
	//код путевки int
	private Recreants waypaperSnn;

	@ManyToOne
	@JoinColumn(name = "c_aeger")
	//ИНН заболевания int
	private WorkArea cAeger;

	public Events(int id, Recreants waypaperSnn, WorkArea cAeger) {
		this.id = id;
		this.waypaperSnn = waypaperSnn;
		this.cAeger = cAeger;
	}

	public Events() {
	}

	public Events(Recreants waypaperSnn,
				  WorkArea cAeger) {
		this.waypaperSnn = waypaperSnn;
		this.cAeger = cAeger;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Recreants getWaypaperSnn() {
		return waypaperSnn;
	}

	public void setWaypaperSnn(Recreants waypaperSnn) {
		this.waypaperSnn = waypaperSnn;
	}

	public WorkArea getcAeger() {
		return cAeger;
	}

	public void setcAeger(WorkArea cAeger) {
		this.cAeger = cAeger;
	}
}
