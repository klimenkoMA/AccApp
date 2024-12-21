package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Сотрудники
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "fio")
    private String fio;
    @Column(name = "dborn")
    private String dborn;
    @Column
    private Profession profession;
    @ManyToOne
    @JoinColumn(name = "workarea")
    private WorkArea workarea;
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Devices> devices;

    public Employee() {
    }

    public Employee(int id
            , String fio
            , String dborn
            , Profession profession
            , WorkArea workarea
            , Room room) {
        this.id = id;
        this.fio = fio;
        this.dborn = dborn;
        this.profession = profession;
        this.workarea = workarea;
        this.room = room;
    }

    public Employee(String fio
            , String dborn
            , Profession profession
            , WorkArea workarea
            , Room room) {
        this.fio = fio;
        this.dborn = dborn;
        this.profession = profession;
        this.workarea = workarea;
        this.room = room;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
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

    public String getDborn() {
        return dborn;
    }

    public void setDborn(String dborn) {
        this.dborn = dborn;
    }

    public WorkArea getWorkarea() {
        return workarea;
    }

    public void setWorkarea(WorkArea workArea) {
        this.workarea = workArea;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void addEvent(Events event) {
        if (event != null) {
            events.add(event);
        }
    }

    @Override
    public String toString() {
        return fio;
    }
}
