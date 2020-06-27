package net.itspartner.rooms_with_light_bulbs.service;


import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;
import java.util.Set;

public interface RoomService {

    boolean addNewRoom(Room room) throws ServiceException;

    boolean checkIsRoomNameFree(String name) throws ServiceException;

    void changeLightStatus(int value, String name) throws ServiceException;

    List<Room> getAllRooms() throws ServiceException;

    Set<String> getAllCountries() throws ServiceException;

}