package accountingApp.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Отдыхающий
 */
@Entity
@Table(name = "recreants")
public class Recreants implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "waypaper_snn")
	// Серия и номер путевки
	private String waypaperSnn;

	@Column(name = "fio")
	// Фамилия Имя Очество
	private String fio;

	@Column(name = "d_born")
	//Дата рождения
	private String dBorn;

	@Column(name = "livecity")
	// Город проживания
	private String liveCity;

	@ManyToOne
	@JoinColumn(name = "c_profession")
	// ИНН профессии
	private ProfCaptions cProfession;

	@ManyToOne
	@JoinColumn(name = "doctor")
	// ИНН доктора
	private Employee doctor;

	@Column(name = "waypaper_date")
	//Дата путевки
	private String waypaperDate;

	@Column(name = "d_arrival")
	//Дата прибытия
	private String dArrival;

	@Column(name = "d_leave")
	//Дата убытия
	private String dLeave;

	@Column(name = "d_revision")
	//Дата осмотра
	private String dRevision;

	@Column(name = "corpus")
	//Корпус проживания
	private String corpus;

	@Column(name = "room")
	//Комната проживания
	private String room;

	@Column(name = "place")
	//Место проживания
	private String place;

	@OneToMany(mappedBy = "recreantsSnn")
	private List<ProceduresAssigned> waypaper_snnProcedures;


	@OneToMany(mappedBy = "waypaperSnn")
	private List<RecreantsAegers> waypaper_snnRecreants;


	public Recreants() {
	}

	public Recreants(String waypaperSnn, String fio, String dBorn,
					 String liveCity, ProfCaptions cProfession,
					 Employee doctor, String waypaperDate, String dArrival,
					 String dLeave, String dRevision, String corpus, String room,
					 String place) {
		this.waypaperSnn = waypaperSnn;
		this.fio = fio;
		this.dBorn = dBorn;
		this.liveCity = liveCity;
		this.cProfession = cProfession;
		this.doctor = doctor;
		this.waypaperDate = waypaperDate;
		this.dArrival = dArrival;
		this.dLeave = dLeave;
		this.dRevision = dRevision;
		this.corpus = corpus;
		this.room = room;
		this.place = place;
	}

	public Recreants(int id, String waypaperSnn, String fio, String dBorn,
					 String liveCity, ProfCaptions cProfession,
					 Employee doctor, String waypaperDate, String dArrival,
					 String dLeave, String dRevision, String corpus, String room,
					 String place) {
		this.id = id;
		this.waypaperSnn = waypaperSnn;
		this.fio = fio;
		this.dBorn = dBorn;
		this.liveCity = liveCity;
		this.cProfession = cProfession;
		this.doctor = doctor;
		this.waypaperDate = waypaperDate;
		this.dArrival = dArrival;
		this.dLeave = dLeave;
		this.dRevision = dRevision;
		this.corpus = corpus;
		this.room = room;
		this.place = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWaypaperSnn() {
		return waypaperSnn;
	}

	public void setWaypaperSnn(String waypaperSnn) {
		this.waypaperSnn = waypaperSnn;
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

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public ProfCaptions getcProfession() {
		return cProfession;
	}

	public void setcProfession(ProfCaptions cProfession) {
		this.cProfession = cProfession;
	}

	public Employee getDoctor() {
		return doctor;
	}

	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}

	public String getWaypaperDate() {
		return waypaperDate;
	}

	public void setWaypaperDate(String waypaperDate) {
		this.waypaperDate = waypaperDate;
	}

	public String getdArrival() {
		return dArrival;
	}

	public void setdArrival(String dArrival) {
		this.dArrival = dArrival;
	}

	public String getdLeave() {
		return dLeave;
	}

	public void setdLeave(String dLeave) {
		this.dLeave = dLeave;
	}

	public String getdRevision() {
		return dRevision;
	}

	public void setdRevision(String dRevision) {
		this.dRevision = dRevision;
	}

	public String getCorpus() {
		return corpus;
	}

	public void setCorpus(String corpus) {
		this.corpus = corpus;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<ProceduresAssigned> getWaypaper_snnProcedures() {
		return waypaper_snnProcedures;
	}

	public void setWaypaper_snnProcedures(List<ProceduresAssigned> waypaper_snnProcedures) {
		this.waypaper_snnProcedures = waypaper_snnProcedures;
	}

	public List<RecreantsAegers> getWaypaper_snnRecreants() {
		return waypaper_snnRecreants;
	}

	public void setWaypaper_snnRecreants(List<RecreantsAegers> waypaper_snnRecreants) {
		this.waypaper_snnRecreants = waypaper_snnRecreants;
	}

	@Override
	public String toString() {
		return waypaperSnn;
	}
}

