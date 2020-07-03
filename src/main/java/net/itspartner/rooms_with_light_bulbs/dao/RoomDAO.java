package net.itspartner.rooms_with_light_bulbs.dao;

import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;
import java.util.Set;

/**
 * The interface Room dao.
 */
public interface RoomDAO {

    /**
     * Add new room boolean.
     *
     * @param room the room
     * @return the boolean
     * @throws DAOException the dao exception
     */
    boolean addNewRoom(Room room) throws DAOException;

    /**
     * Check is room name free boolean.
     *
     * @param name the name
     * @return the boolean
     * @throws DAOException the dao exception
     */
    boolean checkIsRoomNameFree(String name) throws DAOException;

    /**
     * Change light status.
     *
     * @param value the value
     * @param name  the name
     * @throws DAOException the dao exception
     */
    void changeLightStatus(int value, String name) throws DAOException;

    /**
     * Gets all rooms.
     *
     * @return the all rooms
     * @throws DAOException the dao exception
     */
    List<Room> getAllRooms() throws DAOException;

    /**
     * Gets room info.
     *
     * @param name the name
     * @return the room info
     * @throws DAOException the dao exception
     */
    Room getRoomInfo(String name) throws DAOException;

    /**
     * Gets all countries.
     *
     * @return the all countries
     * @throws DAOException the dao exception
     */
    Set<String> getAllCountries() throws DAOException;

    /**
     * Gets id of country by name.
     *
     * @param country the country
     * @return the id of country by name
     * @throws DAOException the dao exception
     */
    int getIdOfCountryByName(String country) throws DAOException;

}
