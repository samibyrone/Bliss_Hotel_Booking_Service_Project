package org.Bliss.services;

import org.Bliss.data.model.Booking;
import org.Bliss.data.model.Room;
import org.Bliss.data.model.RoomStatus;
import org.Bliss.data.model.RoomType;
import org.Bliss.data.repositories.BookingRepo;
import org.Bliss.data.repositories.RoomRepo;
import org.Bliss.dtos.request.RoomRequest;
import org.Bliss.dtos.response.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServicesImpl implements RoomServices {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public RoomResponse checkAvailability(RoomRequest roomRequest) {
        RoomType roomType = roomRequest.getRoomType();
        Date checkInDate = roomRequest.getCheckInDate();
        Date checkOutDate = roomRequest.getCheckOutDate();

        List<Room> availableRooms = roomRepo.findAllByRoomTypeAndStatus(roomType, RoomStatus.AVAILABLE);
        List<Room> filteredAvailableRooms = new ArrayList<>();
        for (Room room : availableRooms) {
            boolean isAvailable = true;
            List<Booking> bookings = bookingRepo.findByRoomAndCheckInDateLessThanEqualAndAndCheckOutDateGreaterThanEqual(room, checkInDate, checkOutDate);
            if(!bookings.isEmpty()) {
                isAvailable = false;
            }
            if(isAvailable) {
                filteredAvailableRooms.add(room);
            }
        }

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setAvailableRooms(filteredAvailableRooms);
        return roomResponse;

    }

    private List<Room> filter

    @Override
    public RoomResponse bookRoom(RoomRequest roomRequest) {
        return null;
    }

    @Override
    public RoomResponse updateRoomStatus(RoomRequest roomRequest) {
        return null;
    }
}
