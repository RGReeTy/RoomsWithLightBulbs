package net.itspartner.rooms_with_light_bulbs.service;


import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;

public interface RoomService {

    boolean addNewRoom(Room room) throws ReceiverException;

    boolean checkIsRoomNameFree(String name) throws ReceiverException;

    void changeLightStatus(int value, String name) throws ReceiverException;

    List<Room> getAllRooms() throws ReceiverException;

}