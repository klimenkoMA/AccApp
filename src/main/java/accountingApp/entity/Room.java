package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Названия процедур
 */
@Entity
@Table(name = "proced_captions")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_proc")
	//ИНН процедуры
	//int	RecreationDB.dbo.PROCED_CAPTIONS.PK__PROCED_C__F087B8841BFD2C07
	private int cProc;

	@Column(name = "caption")
	//Название процедуры
	private String caption;

	@OneToMany(mappedBy = "procedCaptions")
	private List<Devices> devices;
	@OneToMany(mappedBy = "procedCaptions")
	private List<ProcForAegers> procForAegers;

	public Room() {

	}

	public Room(int cProc, String caption) {
		this.cProc = cProc;
		this.caption = caption;
	}

	public Room(String caption) {
		this.caption = caption;
	}

	public List<Devices> getProceduresAssigneds() {
		return devices;
	}

	public void setProceduresAssigneds(List<Devices> devices) {
		this.devices = devices;
	}

	public List<ProcForAegers> getProcForAegers() {
		return procForAegers;
	}

	public void setProcForAegers(List<ProcForAegers> procForAegers) {
		this.procForAegers = procForAegers;
	}

	public int getcProc() {
		return cProc;
	}

	public void setcProc(int cProc) {
		this.cProc = cProc;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public String toString() {
		return caption;
	}
}

