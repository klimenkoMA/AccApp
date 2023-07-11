package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Название заболевания
 */
@Entity
@Table(name = "aeger_captions")
public class WorkArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_aeger")
	//ИНН заболевания
	private int cAeger;
	@Column(name = "caption")
	// Название заболевания
	// nvarchar(25)
	private String caption;
	@OneToMany(mappedBy = "cAeger")
	private List<Events> recreantsAegers;
	@OneToMany(mappedBy = "aegerCaptions")
	private List<ProcForAegers> procForAegers;
	@OneToMany(mappedBy = "cAeger")
	private List<ProfAegers> profAegers;

	public WorkArea(int cAeger, String caption) {
		this.cAeger = cAeger;
		this.caption = caption;
	}

	public WorkArea() {
	}

	public WorkArea(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return caption;
	}

	public List<Events> getRecreantsAegers() {
		return recreantsAegers;
	}

	public void setRecreantsAegers(List<Events> recreantsAegers) {
		this.recreantsAegers = recreantsAegers;
	}

	public List<ProcForAegers> getProcForAegers() {
		return procForAegers;
	}

	public void setProcForAegers(List<ProcForAegers> procForAegers) {
		this.procForAegers = procForAegers;
	}

	public List<ProfAegers> getProfAegers() {
		return profAegers;
	}

	public void setProfAegers(List<ProfAegers> profAegers) {
		this.profAegers = profAegers;
	}

	public int getcAeger() {
		return cAeger;
	}

	public void setcAeger(int cAeger) {
		this.cAeger = cAeger;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
}
