package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Виды
 * пользователей
 **/
@Entity
@Table(name = "IT_STAFF")//persons_prof
public class ITStaff {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "NAME") //name_rec
	//Название вида пользователей nvarchar(50)
	private String name;

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
