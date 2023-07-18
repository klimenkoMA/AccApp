package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Названия процедур
 */
@Entity
@Table(name = "ROOM") //proced_captions
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") //c_proc
    private int id;

    @Column(name = "number") //caption
    //Название процедуры
    private String number;

    public Room() {
    }

    public Room(String number) {
        this.number = number;
    }

    public Room(int id, String number) {
        this.id = id;
        this.number = number;
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

