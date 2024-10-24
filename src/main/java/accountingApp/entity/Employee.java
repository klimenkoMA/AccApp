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

    @Column(name = "workarea")
    private String workarea;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;

    public Employee() {
    }

    public Employee(int id, String fio, String dborn, String workarea, Room room) {
        this.id = id;
        this.fio = fio;
        this.dborn = dborn;
        this.workarea = workarea;
        this.room = room;
    }

    public Employee(String fio, String dborn, String workarea, Room room) {
        this.fio = fio;
        this.dborn = dborn;
        this.workarea = workarea;
        this.room = room;
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

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workArea) {
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
