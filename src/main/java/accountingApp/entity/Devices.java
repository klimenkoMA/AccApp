package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Устройства для выдачи
 */
@Entity
@Table(name = "devices")
public class Devices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column
    private String description;
    @Column
    private Long inventory;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;

    public Devices() {
    }

    public Devices(int id, String name, String description, long inventory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    public Devices(String name, String description, long inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
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

    @Override
    public String toString() {
        return name;
    }
}

