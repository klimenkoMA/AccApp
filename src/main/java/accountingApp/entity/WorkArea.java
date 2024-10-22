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
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;

    public WorkArea() {
    }

    public WorkArea(String name, Room room) {
        this.name = name;
        this.room = room;
    }

    public WorkArea(int id, String name, Room room) {
        this.id = id;
        this.name = name;
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
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
