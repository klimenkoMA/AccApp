package accountingApp.entity;

import javax.persistence.*;

/**
 * Выполнение процедур
 */
@Entity
@Table(name = "DEVICES") //procedures_assigned
public class Devices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID") //n_assigned
	private int id;

	@Column(name = "NAME") //n_passed
	// пройдено процедур int
	private int name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
}

