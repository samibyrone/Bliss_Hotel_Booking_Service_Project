package org.Bliss.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
public class Room {
    @Id
    private String roomId;
    private String roomNumber;
    private RoomType roomType;
    private BigDecimal pricePerNight;
    private RoomStatus status;
}
