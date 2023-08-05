package accountingApp.entity;

import javax.persistence.*;
import java.util.Set;


/**
 * Виды
 * пользователей
 **/
@Entity
@Table(name = "itstaff")//persons_prof
public class ITStaff {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name") //name_rec
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Events> events;

    public ITStaff() {
    }

    public ITStaff(String name) {
        this.name = name;
    }

    public ITStaff(int id, String name) {
        this.id = id;
        this.name = name;
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
}
