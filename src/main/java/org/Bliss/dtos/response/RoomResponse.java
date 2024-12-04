package org.Bliss.dtos.response;

import lombok.Data;
import org.Bliss.data.model.Room;

import java.util.List;
@Data
public class RoomResponse {
    private List<Room> availableRooms;
    private String message;
    private Room bookedRoom;

}
