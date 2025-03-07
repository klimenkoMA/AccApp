package accountingApp.entity.dto.devicesdto;

public class MaxOwnerCountDTO {

    private String owner;
    private Long devicesCount;

    public MaxOwnerCountDTO(String owner, Long devicesCount) {
        this.owner = owner;
        this.devicesCount = devicesCount;
    }

    public MaxOwnerCountDTO() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getDevicesCount() {
        return devicesCount;
    }

    public void setDevicesCount(Long devicesCount) {
        this.devicesCount = devicesCount;
    }
}
