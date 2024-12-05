package org.Bliss.dtos.request;

import lombok.Data;

import java.util.Date;

@Data
public class BookingRequest {
    private String userId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private String bookingId;
}
