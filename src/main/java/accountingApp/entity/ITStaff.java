package accountingApp.entity;

import javax.persistence.*;
import java.util.List;


/**
 * Программисты
 **/
@Entity
@Table(name = "itstaff")
public class ITStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;

    public ITStaff() {
    }

    public ITStaff(String name) {
        this.name = name;
    }

    public ITStaff(int id, String name) {
        this.id = id;
        this.name = name;
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

    public void addEvent(Events event) {
        if (event != null) {
            events.add(event);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
