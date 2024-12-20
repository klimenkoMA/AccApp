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
    @Column
    private DeviceCategory category;
    @Column(name = "name")
    private String name;
    @Column
    private String description;
    @Column
    private Long inventory;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Events> events;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room")
    private Room room;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "keeper")
    private Employee employee;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private ITStaff itstaff;

    public Devices() {
    }

    public Devices(int id
            , DeviceCategory category
            , String name
            , String description
            , Long inventory
            , Room room
            , Employee employee
            , ITStaff itstaff) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.room = room;
        this.employee = employee;
        this.itstaff = itstaff;
    }

    public Devices(DeviceCategory category
            , String name
            , String description
            , Long inventory
            , Room room
            , Employee employee
            , ITStaff itstaff) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.room = room;
        this.employee = employee;
        this.itstaff = itstaff;
    }

    public DeviceCategory getCategory() {
        return category;
    }

    public void setCategory(DeviceCategory category) {
        this.category = category;
    }

    public ITStaff getItstaff() {
        return itstaff;
    }

    public void setItstaff(ITStaff itStaff) {
        this.itstaff = itStaff;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

