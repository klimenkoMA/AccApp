package accountingApp.entity;

import javax.persistence.*;

/**
 * События
 */
@Entity
@Table(name = "EVENTS")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE")
    private String date;

    @ManyToOne
    @JoinColumn(name = "deviceid")
    private Devices device;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEID")
    private Employee employeeid;

    @ManyToOne
    @JoinColumn(name = "ITSTAFFID")
    private ITStaff itstaffid;

    @ManyToOne
    @JoinColumn(name = "WORKAREA")
    private WorkArea workarea;

    @Column(name = "COMMENT")
    private String comment;

    public Events(String date, Devices device,
                  Employee employeeid, ITStaff itstaffid,
                  WorkArea workarea, String comment) {
        this.date = date;
        this.device = device;
        this.employeeid = employeeid;
        this.itstaffid = itstaffid;
        this.workarea = workarea;
        this.comment = comment;
    }

    public Events(int id, String date, Devices device,
                  Employee employeeid, ITStaff itstaffid,
                  WorkArea workarea, String comment) {
        this.id = id;
        this.date = date;
        this.device = device;
        this.employeeid = employeeid;
        this.itstaffid = itstaffid;
        this.workarea = workarea;
        this.comment = comment;
    }

    public Events() {
    }



    public WorkArea getWorkarea() {
        return workarea;
    }

    public void setWorkarea(WorkArea workarea) {
        this.workarea = workarea;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public Employee getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Employee employeeId) {
        this.employeeid = employeeId;
    }

    public ITStaff getItstaffid() {
        return itstaffid;
    }

    public void setItstaffid(ITStaff itStaffId) {
        this.itstaffid = itStaffId;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", device=" + device +
                ", employeeid=" + employeeid +
                ", itstaffid=" + itstaffid +
                '}';
    }
}
