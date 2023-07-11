package accountingApp.entity;

import javax.persistence.*;

/**
 * Пользователи
 */
@Entity
@Table(name = "persons")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// Уникальный номер int
	private int id;
	@Column(name = "fio")
	// Фамилия Имя Очество nvarchar(50)
	private String fio;
	@Column(name = "login")
	// логин char(8)
	private String login;
	@Column(name = "passwd")
	// пароль char(8)
	private String password;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "prof_id")
	// Цифра профессии tinyint
	private ITStaff prof_id;

	public Employee() {
	}

	public Employee(int id, String fio, String login, String password, ITStaff prof_id) {
		this.id = id;
		this.fio = fio;
		this.login = login;
		this.password = password;
		this.prof_id = prof_id;
	}

	public Employee(String fio, String login, String password, ITStaff prof_id) {
		this.fio = fio;
		this.login = login;
		this.password = password;
		this.prof_id = prof_id;
	}

	@Override
	public String toString() {
		return fio;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ITStaff getProf_id() {
		return prof_id;
	}

	public void setProf_id(ITStaff prof_id) {
		this.prof_id = prof_id;
	}
}
