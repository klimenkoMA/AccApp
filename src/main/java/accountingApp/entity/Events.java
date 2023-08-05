package accountingApp.entity;

import javax.persistence.*;

/**
 * Диагноз
 */
@Entity
@Table(name = "EVENTS")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE")
    private int date;

    @ManyToOne
    @JoinColumn(name = "deviceid")
    private Devices device;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEID")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "ITSTAFFID")
    private ITStaff itStaffId;

    public Events() {
    }

    public Events(int date, Devices device, Employee employeeId, ITStaff itStaffId) {
        this.date = date;
        this.device = device;
        this.employeeId = employeeId;
        this.itStaffId = itStaffId;
    }

    public Events(int id, int date, Devices device, Employee employeeId, ITStaff itStaffId) {
        this.id = id;
        this.date = date;
        this.device = device;
        this.employeeId = employeeId;
        this.itStaffId = itStaffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Devices getDevice() {
        return device;
    }

    public void setDevice(Devices device) {
        this.device = device;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public ITStaff getItStaffId() {
        return itStaffId;
    }

    public void setItStaffId(ITStaff itStaffId) {
        this.itStaffId = itStaffId;
    }
}
