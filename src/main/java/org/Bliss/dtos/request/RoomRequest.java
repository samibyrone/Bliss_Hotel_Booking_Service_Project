package org.Bliss.dtos.request;

import lombok.Data;
import org.Bliss.data.model.RoomStatus;
import org.Bliss.data.model.RoomType;

import java.util.Date;
@Data
public class RoomRequest {
    private RoomType roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private String roomId;
    private RoomStatus newStatus;

}
