package org.Bliss.services;

import org.Bliss.data.model.*;
import org.Bliss.data.repositories.BookingRepo;
import org.Bliss.data.repositories.RoomRepo;
import org.Bliss.dtos.request.RoomRequest;
import org.Bliss.dtos.response.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            List<Booking> bookings = bookingRepo.findByRoomAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(room, checkInDate, checkOutDate);
            if (!bookings.isEmpty()) {
                isAvailable = false;
            }
            if (isAvailable) {
                filteredAvailableRooms.add(room);
            }
        }

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setAvailableRooms(filteredAvailableRooms);
        return roomResponse;

    }

    private List<Room> filterAvailableRooms(List<Room> availableRooms, Date checkInDate, Date checkOutDate) {
        List<Room> filteredAvailableRooms = new ArrayList<>();
        for (Room room : availableRooms) {
            boolean isAvailable = true;
            List<Booking> bookings = bookingRepo.findByRoomAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(room, checkInDate, checkOutDate);
            if (!bookings.isEmpty()) {
                isAvailable = false;
            }
            if (isAvailable) {
                filteredAvailableRooms.add(room);
            }
        }
        return filteredAvailableRooms;
    }

    @Override
    public RoomResponse bookRoom(RoomRequest roomRequest){
        RoomType roomType = roomRequest.getRoomType();
        Date checkInDate = roomRequest.getCheckInDate();
        Date checkOutDate = roomRequest.getCheckOutDate();

        List<Room> availableRooms = roomRepo.findAllByRoomTypeAndStatus(roomType, RoomStatus.AVAILABLE);
        List<Room> filteredAvailableRooms = filterAvailableRooms(availableRooms, checkInDate, checkOutDate);
        if(filteredAvailableRooms.isEmpty()){
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setMessage("No room available at the specified date");
        }
        for(Room room : filteredAvailableRooms){
            if(room.getStatus() == RoomStatus.AVAILABLE){
                room.setStatus(RoomStatus.UNAVAILABLE);
                roomRepo.save(room);

                Booking booking = new Booking();
                booking.setRoom(room);
                booking.setUser(new User());
                booking.setCheckInDate(checkInDate);
                booking.setCheckOutDate(checkOutDate);
                bookingRepo.save(booking);
                RoomResponse roomResponse = new RoomResponse();
                roomResponse.setMessage("Room booked successfully");
                return roomResponse;
            }
        }
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setMessage("Room not available at the specified date");
        return roomResponse;
    }



    @Override
    public RoomResponse updateRoomStatus (RoomRequest roomRequest){
        String roomId = roomRequest.getRoomId();

        Optional<Room> optionalRoom = roomRepo.findById(roomId);
        if(optionalRoom.isEmpty()){
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setMessage("Room not found");
            return roomResponse;
        }

        Room roomToUpdate = optionalRoom.get();
        RoomStatus newStatus = roomRequest.getNewStatus();
        roomToUpdate.setStatus(newStatus);
        roomRepo.save(roomToUpdate);

        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setMessage("Room updated successfully");
        return roomResponse;
    }

}