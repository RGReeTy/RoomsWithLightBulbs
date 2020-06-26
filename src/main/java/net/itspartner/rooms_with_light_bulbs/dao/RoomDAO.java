package net.itspartner.rooms_with_light_bulbs.dao;

import net.itspartner.rooms_with_light_bulbs.bean.Room;

import java.util.List;

public interface RoomDAO {

    boolean addNewRoom();

    boolean checkIsRoomNameFree();

    void changeLightStatus();

    List<Room> getAllRooms();

}
