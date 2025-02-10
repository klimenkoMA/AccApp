package accountingApp.entity.dto.roomdto;

public class MaxRoomCountDTO {

    private int roomsCount;
    private String workAreaName;

    public MaxRoomCountDTO(int roomsCount, String workAreaName) {
        this.roomsCount = roomsCount;
        this.workAreaName = workAreaName;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public String getWorkAreaName() {
        return workAreaName;
    }

    public void setWorkAreaName(String workAreaName) {
        this.workAreaName = workAreaName;
    }
}
