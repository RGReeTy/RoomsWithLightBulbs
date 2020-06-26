package net.itspartner.rooms_with_light_bulbs.dao;

import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;

public interface RoomDAO {

    boolean addNewRoom(Room room) throws DAOException;

    boolean checkIsRoomNameFree(String name) throws DAOException;

    void changeLightStatus(int value, String name) throws DAOException;

    List<Room> getAllRooms() throws DAOException;

}
