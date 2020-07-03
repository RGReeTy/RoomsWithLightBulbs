package net.itspartner.rooms_with_light_bulbs.service;


import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;
import java.util.Set;

/**
 * The interface Room service.
 */
public interface RoomService {

    /**
     * Add new room boolean.
     *
     * @param room the room
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addNewRoom(Room room) throws ServiceException;

    /**
     * Check is room name free boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkIsRoomNameFree(String name) throws ServiceException;

    /**
     * Change light status.
     *
     * @param status the status
     * @param name   the name
     * @throws ServiceException the service exception
     */
    void changeLightStatus(boolean status, String name) throws ServiceException;

    /**
     * Gets all rooms.
     *
     * @return the all rooms
     * @throws ServiceException the service exception
     */
    List<Room> getAllRooms() throws ServiceException;

    /**
     * Gets room info.
     *
     * @param name the name
     * @return the room info
     * @throws ServiceException the service exception
     */
    Room getRoomInfo(String name) throws ServiceException;

    /**
     * Gets all countries.
     *
     * @return the all countries
     * @throws ServiceException the service exception
     */
    Set<String> getAllCountries() throws ServiceException;

    /**
     * Gets id of country by name.
     *
     * @param country the country
     * @return the id of country by name
     * @throws ServiceException the service exception
     */
    int getIdOfCountryByName(String country) throws ServiceException;

}