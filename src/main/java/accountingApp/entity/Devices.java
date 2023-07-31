package accountingApp.entity;

import javax.persistence.*;

/**
 * Выполнение процедур
 */
@Entity
@Table(name = "devices") //procedures_assigned
public class Devices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") //n_assigned
	private Integer id;

	@Column(name = "name") //n_passed пройдено процедур int
	private String name;

	public Devices() {
	}

	public Devices(String name) {
		this.name = name;
	}

	public Devices(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

