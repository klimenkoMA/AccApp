package accountingApp.entity.dto.employeedto;

public class MaxEmployeesInWorkAreaDTO {

    private int employeesCount;
    private String workAreaName;

    public MaxEmployeesInWorkAreaDTO() {
    }

    public MaxEmployeesInWorkAreaDTO(int employeesCount, String workAreaName) {
        this.employeesCount = employeesCount;
        this.workAreaName = workAreaName;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }
}
