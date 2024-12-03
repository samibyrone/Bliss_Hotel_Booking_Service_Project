package org.Bliss.services;

import org.Bliss.dtos.request.BookingRequest;
import org.Bliss.dtos.response.BookingResponse;

public interface BookingService {
    BookingResponse createBooking (BookingRequest bookingRequest);
    BookingResponse cancelBooking (BookingRequest bookingRequest);
    BookingResponse getBookingDetails (BookingRequest bookingRequest);
}
