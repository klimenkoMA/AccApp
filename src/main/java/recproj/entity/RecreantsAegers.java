package recproj.entity;

import javax.persistence.*;

/**
 * Диагноз
 */
@Entity
@Table(name = "recreants_aegers")
public class RecreantsAegers {

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
	private AegerCaptions cAeger;

	public RecreantsAegers(int id, Recreants waypaperSnn, AegerCaptions cAeger) {
		this.id = id;
		this.waypaperSnn = waypaperSnn;
		this.cAeger = cAeger;
	}

	public RecreantsAegers() {
	}

	public RecreantsAegers(Recreants waypaperSnn,
	                       AegerCaptions cAeger) {
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

	public AegerCaptions getcAeger() {
		return cAeger;
	}

	public void setcAeger(AegerCaptions cAeger) {
		this.cAeger = cAeger;
	}
}
