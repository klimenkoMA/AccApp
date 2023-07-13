package accountingApp.entity;

import javax.persistence.*;

/**
 * Пользователи
 */
@Entity
@Table(name = "EMPLOYEE") //persons
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	// Уникальный номер int
	private int id;
	@Column(name = "FIO")
	// Фамилия Имя Очество nvarchar(50)
	private String fio;
	@Column(name = "D_BORN") // login
	//  дата рождения SHORT DATE
	private String dBorn;
	@Column(name = "WORK_AREA") // password
	// место работы nvarchar(50)
	private String workArea;

	@Column(name = "ROOM") // prof_id
	// кабинет nvarchar(10)
	private String room;

	public Employee() {
	}

	public Employee(int id, String fio, String dBorn, String workArea, String room) {
		this.id = id;
		this.fio = fio;
		this.dBorn = dBorn;
		this.workArea = workArea;
		this.room = room;
	}

	public Employee(String fio, String dBorn, String workArea, String room) {
		this.fio = fio;
		this.dBorn = dBorn;
		this.workArea = workArea;
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getdBorn() {
		return dBorn;
	}

	public void setdBorn(String dBorn) {
		this.dBorn = dBorn;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}


}
