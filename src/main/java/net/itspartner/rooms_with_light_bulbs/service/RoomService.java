package net.itspartner.rooms_with_light_bulbs.service;


import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;
import java.util.Set;

public interface RoomService {

    boolean addNewRoom(Room room) throws ServiceException;

    boolean checkIsRoomNameFree(String name) throws ServiceException;

    void changeLightStatus(boolean status, String name) throws ServiceException;

    List<Room> getAllRooms() throws ServiceException;

    Room getRoomInfo(String name) throws ServiceException;

    Set<String> getAllCountries() throws ServiceException;

    int getIdOfCountryByName(String country) throws ServiceException;

}