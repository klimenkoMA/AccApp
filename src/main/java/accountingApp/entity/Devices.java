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

	@Column(name = "NAME") //n_passed пройдено процедур int
	private String name;

	public Devices() {
	}

	public Devices(String name) {
		this.name = name;
	}

	public Devices(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

