package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Виды
 * пользователей
 **/
@Entity
@Table(name = "persons_prof")
public class PersonsProf {

	@Override
	public String toString() {
		return nameRec;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(mappedBy = "prof_id")
	// Цифра вида пользователей
	private List<Employee> cMenu;

	@Column(name = "name_rec")
	//Название вида пользователей nvarchar(50)
	private String nameRec;

	public PersonsProf(String nameRec) {
		this.nameRec = nameRec;
	}

	public PersonsProf() {
	}

	public PersonsProf(int id, List<Employee> cMenu, String nameRec) {
		this.id = id;
		this.cMenu = cMenu;
		this.nameRec = nameRec;
	}

	public PersonsProf(int id) {
		this.id = id;
	}

	public PersonsProf(int id, String nameRec) {
		this.id = id;
		this.nameRec = nameRec;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Employee> getcMenu() {
		return cMenu;
	}

	public void setcMenu(List<Employee> cMenu) {
		this.cMenu = cMenu;
	}

	public String getNameRec() {
		return nameRec;
	}

	public void setNameRec(String nameRec) {
		this.nameRec = nameRec;
	}

	public void addPersons(Employee employee){
		if(employee != null){
			cMenu.add(employee);
		}
	}
}
