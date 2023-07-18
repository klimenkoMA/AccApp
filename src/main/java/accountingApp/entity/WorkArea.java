package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Название заболевания
 */
@Entity
@Table(name = "WORK_AREA") //aeger_captions
public class WorkArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") //c_aeger
    private int id;
    @Column(name = "AREA_NAME") //caption
    private String areaName;

    public WorkArea() {
    }

    public WorkArea(String areaName) {
        this.areaName = areaName;
    }

    public WorkArea(int id, String areaName) {
        this.id = id;
        this.areaName = areaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
