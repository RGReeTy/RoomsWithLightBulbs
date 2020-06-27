package net.itspartner.rooms_with_light_bulbs.service;

import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.dao.DAOException;
import net.itspartner.rooms_with_light_bulbs.dao.RoomDAO;
import net.itspartner.rooms_with_light_bulbs.dao.factory.DAOFactory;
import net.itspartner.rooms_with_light_bulbs.dao.factory.DAOFactoryProvider;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;


public class RoomServiceImpl implements RoomService {

    private static final Logger logger = Logger.getLogger(RoomServiceImpl.class);

    private final DAOFactory daoFactory = DAOFactoryProvider.getSqlDaoFactory();
    private RoomDAO roomDao = daoFactory.getCountryDao();

    @Override
    public boolean addNewRoom(Room room) throws ServiceException {
        try {
            return roomDao.addNewRoom(room);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean checkIsRoomNameFree(String name) throws ServiceException {
        try {
            return roomDao.checkIsRoomNameFree(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeLightStatus(int value, String name) throws ServiceException {
        try {
            roomDao.changeLightStatus(value, name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Room> getAllRooms() throws ServiceException {
        try {
            return roomDao.getAllRooms();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<String> getAllCountries() throws ServiceException {
        try {
            return roomDao.getAllCountries();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}