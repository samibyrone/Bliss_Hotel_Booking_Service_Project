package org.Bliss.services;

import org.Bliss.dtos.request.RoomRequest;
import org.Bliss.dtos.response.RoomResponse;

public interface RoomServices {
    RoomResponse createSender (RoomRequest roomRequest);
}
