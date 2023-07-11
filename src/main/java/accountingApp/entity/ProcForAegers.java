package accountingApp.entity;

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
	private WorkArea workArea;

	@ManyToOne
	@JoinColumn(name = "c_proced")
	// ИНН процедуры
	private Room room;

	public ProcForAegers(int id, WorkArea workArea, Room room) {
		this.id = id;
		this.workArea = workArea;
		this.room = room;
	}

	public ProcForAegers() {

	}

	public ProcForAegers(WorkArea workArea, Room room) {
		this.workArea = workArea;
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WorkArea getAegerCaptions() {
		return workArea;
	}

	public void setAegerCaptions(WorkArea workArea) {
		this.workArea = workArea;
	}

	public Room getProcedCaptions() {
		return room;
	}

	public void setProcedCaptions(Room room) {
		this.room = room;
	}
}
