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
    @OneToMany(cascade = CascadeType.ALL)
    private List<WorkArea> workAreaList;


    public Room() {
    }

    public Room(String number) {
        this.number = number;
    }

    public Room(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public Room(int id, String number, List<WorkArea> workAreaList) {
        this.id = id;
        this.number = number;
        this.workAreaList = workAreaList;
    }

    public Room(String number, List<WorkArea> workAreaList) {
        this.number = number;
        this.workAreaList = workAreaList;
    }

    public List<WorkArea> getWorkAreaList() {
        return workAreaList;
    }

    public void setWorkAreaList(List<WorkArea> workAreaList) {
        this.workAreaList = workAreaList;
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
}

