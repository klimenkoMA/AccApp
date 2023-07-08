package accountingApp.entity;

import javax.persistence.*;

/**
 * Выполнение процедур
 */
@Entity
@Table(name = "procedures_assigned")
public class ProceduresAssigned {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "n_assigned")
	//назначено процедур int
	private int nAssigned;
	@Column(name = "n_passed")
	// пройдено процедур int
	private int nPassed;
	@Column(name = "comment")
	// комментарий врача nvarchar(50)
	private String comment;
	@ManyToOne
	@JoinColumn(name = "waypaper_snn")
	private Recreants recreantsSnn;

	@ManyToOne
	@JoinColumn(name = "c_proc")
	private ProcedCaptions procedCaptions;


	public ProceduresAssigned(int nAssigned, int nPassed, String comment,
	                          Recreants recreantsSnn,
	                          ProcedCaptions procedCaptions) {
		this.nAssigned = nAssigned;
		this.nPassed = nPassed;
		this.comment = comment;
		this.recreantsSnn = recreantsSnn;
		this.procedCaptions = procedCaptions;
	}

	public ProceduresAssigned(int id, int nAssigned, int nPassed, String comment,
	                          Recreants recreantsSnn,
	                          ProcedCaptions procedCaptions) {
		this.id = id;
		this.nAssigned = nAssigned;
		this.nPassed = nPassed;
		this.comment = comment;
		this.recreantsSnn = recreantsSnn;
		this.procedCaptions = procedCaptions;
	}

	public ProceduresAssigned() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getnAssigned() {
		return nAssigned;
	}

	public void setnAssigned(int nAssigned) {
		this.nAssigned = nAssigned;
	}

	public int getnPassed() {
		return nPassed;
	}

	public void setnPassed(int nPassed) {
		this.nPassed = nPassed;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Recreants getRecreantsSnn() {
		return recreantsSnn;
	}

	public void setRecreantsSnn(Recreants recreantsSnn) {
		this.recreantsSnn = recreantsSnn;
	}

	public ProcedCaptions getProcedCaptions() {
		return procedCaptions;
	}

	public void setProcedCaptions(ProcedCaptions procedCaptions) {
		this.procedCaptions = procedCaptions;
	}
}

