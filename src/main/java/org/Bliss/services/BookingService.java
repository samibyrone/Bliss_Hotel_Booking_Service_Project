package org.Bliss.services;

import org.Bliss.dtos.request.BookingRequest;
import org.Bliss.dtos.response.BookingResponse;

public interface BookingService {
    BookingResponse createSender (BookingRequest bookingRequest);
}
