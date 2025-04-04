package accountingApp.entity.dto.employeedto;

public class MaxEmployeesInWorkAreaDTO {

    private String workAreaName;
    private Long employeesCount;

    public MaxEmployeesInWorkAreaDTO() {
    }

    public MaxEmployeesInWorkAreaDTO(String workAreaName, Long employeesCount) {
        this.workAreaName = workAreaName;
        this.employeesCount = employeesCount;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Long employeesCount) {
        this.employeesCount = employeesCount;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }
}
