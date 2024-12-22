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
    @Column
    private Profession profession;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Devices> devices;

    public ITStaff() {
    }

    public ITStaff(String name, Profession profession) {
        this.name = name;
        this.profession = profession;
    }

    public ITStaff(int id, String name, Profession profession) {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public List<Devices> getDevices() {
        return devices;
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
