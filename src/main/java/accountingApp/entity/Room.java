package accountingApp.entity;

import javax.persistence.*;

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

    public Room() {
    }

    public Room(String number, WorkArea workarea) {
        this.number = number;
        this.workarea = workarea;
    }

    public Room(int id, String number, WorkArea workarea) {
        this.id = id;
        this.number = number;
        this.workarea = workarea;
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
}

