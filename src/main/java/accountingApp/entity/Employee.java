package accountingApp.entity;

import javax.persistence.*;

/**
 * Пользователи
 */
@Entity
@Table(name = "EMPLOYEE") //persons
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "dborn") // login
    private String dborn;

    @Column(name = "workarea") // password
    private String workarea;

    @Column(name = "room") // prof_id
    private String room;

    public Employee() {
    }

    public Employee(int id, String fio, String dborn, String workarea, String room) {
        this.id = id;
        this.fio = fio;
        this.dborn = dborn;
        this.workarea = workarea;
        this.room = room;
    }

    public Employee(String fio, String dborn, String workarea, String room) {
        this.fio = fio;
        this.dborn = dborn;
        this.workarea = workarea;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDborn() {
        return dborn;
    }

    public void setDborn(String dborn) {
        this.dborn = dborn;
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workArea) {
        this.workarea = workArea;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
