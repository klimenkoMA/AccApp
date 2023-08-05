package accountingApp.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Устройства для выдачи
 */
@Entity
@Table(name = "devices")
public class Devices {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<Events> events;

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

