package accountingApp.entity.dto.roomdto;

public class MaxRoomCountDTO {

    private String workAreaName;
    private Long roomsCount;

    public MaxRoomCountDTO(String workAreaName, Long roomsCount) {
        this.workAreaName = workAreaName;
        this.roomsCount = roomsCount;
    }

    public MaxRoomCountDTO() {
    }

    public long getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(Long roomsCount) {
        this.roomsCount = roomsCount;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }
}
