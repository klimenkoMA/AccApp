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
    @Column(name = "room")
    private Room room;
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

    public WorkArea(int id, String name, Room room, List<Events> events) {
        this.id = id;
        this.name = name;
        this.room = room;
        this.events = events;
    }

    public WorkArea(String name, Room room, List<Events> events) {
        this.name = name;
        this.room = room;
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
