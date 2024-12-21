package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Номера кабинетов
 */
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "number")
    private String number;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="workarea")
    private WorkArea workarea;
    @OneToMany
    private List<Devices> devices;
    @Column
    private String description;

    public Room() {
    }

    public Room(int id, String number, WorkArea workarea, String description) {
        this.id = id;
        this.number = number;
        this.workarea = workarea;
        this.description = description;
    }

    public Room(String number, WorkArea workarea, String description) {
        this.number = number;
        this.workarea = workarea;
        this.description = description;
    }

    public List<Devices> getDevices() {
        return devices;
    }

    public void setDevices(List<Devices> devices) {
        this.devices = devices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkArea getWorkarea() {
        return workarea;
    }

    public void setWorkarea(WorkArea workArea) {
        this.workarea = workArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return number ;
    }
}

