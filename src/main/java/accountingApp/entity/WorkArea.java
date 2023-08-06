package accountingApp.entity;

import javax.persistence.*;

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

    public WorkArea() {
    }

    public WorkArea(String name) {
        this.name = name;
    }

    public WorkArea(int id, String name) {
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
