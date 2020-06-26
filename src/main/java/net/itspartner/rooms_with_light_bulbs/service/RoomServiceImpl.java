package net.itspartner.rooms_with_light_bulbs.service;

import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.dao.DAOException;
import net.itspartner.rooms_with_light_bulbs.dao.RoomDAO;
import net.itspartner.rooms_with_light_bulbs.dao.factory.DAOFactory;
import net.itspartner.rooms_with_light_bulbs.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;

import java.util.List;


public class RoomServiceImpl implements RoomService {

    private static final Logger logger = Logger.getLogger(RoomServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private RoomDAO roomDao = daoFactory.getCountryDao();

    @Override
    public boolean addNewRoom(Room room) throws ReceiverException {
        try {
            return roomDao.addNewRoom(room);
        } catch (DAOException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public boolean checkIsRoomNameFree(String name) throws ReceiverException {
        try {
            return roomDao.checkIsRoomNameFree(name);
        } catch (DAOException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public void changeLightStatus(int value, String name) throws ReceiverException {
        try {
            roomDao.changeLightStatus(value, name);
        } catch (DAOException e) {
            throw new ReceiverException(e);
        }
    }

    @Override
    public List<Room> getAllRooms() throws ReceiverException {
        try {
            return roomDao.getAllRooms();
        } catch (DAOException e) {
            throw new ReceiverException(e);
        }
    }
}