package net.itspartner.rooms_with_light_bulbs.dao;

import net.itspartner.rooms_with_light_bulbs.bean.Country;
import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.dao.connection_pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private static final Logger logger = Logger.getLogger(RoomDAOImpl.class);

    private final static String SELECT_ALL_ROOMS = "SELECT ROOM.NAME, C.NAME, LIGHT FROM ROOM\n" +
            "JOIN COUNTRY C on ROOM.ID_COUNTRY = C.ID;";


    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static RoomDAOImpl instance = new RoomDAOImpl();

    public RoomDAOImpl() {
    }

    public static RoomDAOImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewRoom() {
        return false;
    }

    @Override
    public boolean checkIsRoomNameFree() {
        return false;
    }

    @Override
    public void changeLightStatus() {

    }

    @Override
    public List<Room> getAllRooms() {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        List<Room> roomList = new ArrayList<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_ROOMS);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Country
                User user = new User();
                user.setId_user(resultSet.getInt("Id_User"));
                user.setLogin(resultSet.getString("Login"));
                user.setLevel_access(resultSet.getInt("Level_access"));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOUserException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(userList.size());
        return userList;
    }
}
