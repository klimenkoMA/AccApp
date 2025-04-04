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
    @Column
    private String description;
    @OneToMany
    private List<Room> rooms;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;
    @OneToMany
    private List<Employee> employees;

    public WorkArea() {
    }

    public WorkArea(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public WorkArea(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
