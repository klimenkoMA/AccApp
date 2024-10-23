package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Филиалы
 */
@Entity
@Table(name = "WORK_AREA")
public class WorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "name")
    private String name;
    @OneToMany
    private List<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;

    public WorkArea() {
    }

    public WorkArea(String name) {
        this.name = name;
    }

    public WorkArea(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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

    @Override
    public String toString() {
        return name;
    }
}
