package recproj.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Названия профессий пациентов
 */
@Entity
@Table(name = "prof_captions")
public class ProfCaptions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_prof")
	//ИНН профессии
	//int
	private int cProf;

	@Column(name = "caption")
	// Название профессии
	// nvarchar(20)
	private String caption;

	@OneToMany(mappedBy = "cProfession")
	private List<Recreants> recreants;

	@OneToMany(mappedBy = "cProf")
	private List<ProfAegers> profAegers;

	public ProfCaptions() {
	}

	public ProfCaptions(int cProf, String caption) {
		this.cProf = cProf;
		this.caption = caption;
	}

	public ProfCaptions(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return caption;
	}

	public List<Recreants> getRecreants() {
		return recreants;
	}

	public void setRecreants(List<Recreants> recreants) {
		this.recreants = recreants;
	}

	public List<ProfAegers> getProfAegers() {
		return profAegers;
	}

	public void setProfAegers(List<ProfAegers> profAegers) {
		this.profAegers = profAegers;
	}

	public int getcProf() {
		return cProf;
	}

	public void setcProf(int cProf) {
		this.cProf = cProf;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}

