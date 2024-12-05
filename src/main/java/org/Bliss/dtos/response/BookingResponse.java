package org.Bliss.dtos.response;

import lombok.Data;
import org.Bliss.data.model.Booking;

@Data
public class BookingResponse {
    private String message;
    private Booking booking;
    private boolean success;
}
