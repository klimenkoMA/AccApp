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

    @JoinColumn(name = "DATE")//waypaper_snn
    //код путевки int
    private int date;

    @JoinColumn(name = "EMPLOYEE_ID") //c_aeger
    //ИНН заболевания int
    private String employeeId;

    @JoinColumn(name = "IT_STAFF_ID")
    //ИНН заболевания int
    private String itStaffId;


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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getItStaffId() {
        return itStaffId;
    }

    public void setItStaffId(String itStaffId) {
        this.itStaffId = itStaffId;
    }
}
