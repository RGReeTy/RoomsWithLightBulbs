package net.itspartner.rooms_with_light_bulbs.dao;

import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.dao.connection_pool.ConnectionPool;
import net.itspartner.rooms_with_light_bulbs.dao.connection_pool.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private static final Logger logger = Logger.getLogger(RoomDAOImpl.class);

    private final static String SELECT_ALL_ROOMS = "SELECT ROOM.NAME, C.NAME AS Country, LIGHT FROM ROOM" +
            " JOIN COUNTRY C on ROOM.ID_COUNTRY = C.ID";
    private final static String CHANGE_LIGHT_STATUS = "UPDATE ROOM SET LIGHT=? WHERE NAME = ?";
    private final static String CHECK_FREE_NAME = "SELECT NAME FROM ROOM WHERE NAME = ?";
    private final static String INSERT_NEW_ROOM = "INSERT INTO ROOM (NAME, ID_COUNTRY, LIGHT) VALUES(?,?,?)";


    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static RoomDAOImpl instance = new RoomDAOImpl();

    public RoomDAOImpl() {
    }

    public static RoomDAOImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewRoom(Room room) throws DAOException {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connectionPool.takeConnection();
            pstmt = conn.prepareStatement(INSERT_NEW_ROOM);
            pstmt.setString(1, room.getRoomsName());
            pstmt.setString(2, room.getCountry());
            pstmt.setBoolean(3, room.isLightStatus());

            int count = pstmt.executeUpdate();
            if (count == 1) {
                flag = true;
                logger.info("Room was succesfully cr8");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't insert user." + e);
            throw new DAOException(e);
        } finally {
            if (conn != null) {
                connectionPool.closeConnection(conn, pstmt);
            }
        }
        return flag;
    }

    @Override
    public boolean checkIsRoomNameFree(String name) throws DAOException {
        boolean isFree = true;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(CHECK_FREE_NAME);
            prepareStatement.setString(1, name);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                isFree = false;
            } else {
                logger.error("resultSet is null");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Can't select user by login. " + e);
            throw new DAOException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement, resultSet);
            }
        }
        logger.info(isFree);
        return isFree;
    }

    @Override
    public void changeLightStatus(int value, String name) throws DAOException {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = connectionPool.takeConnection();
            prepareStatement = connection.prepareStatement(CHANGE_LIGHT_STATUS);
            prepareStatement.setInt(1, value);
            prepareStatement.setString(2, name);
            if (prepareStatement.executeUpdate() != 1) {
                throw new DAOException("Error at update DB");
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Operation UPDATE is broke: " + e);
            throw new DAOException(e);
        } finally {
            if (connection != null) {
                connectionPool.closeConnection(connection, prepareStatement);
            }
        }
    }

    @Override
    public List<Room> getAllRooms() throws DAOException {
        Connection con = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        List<Room> roomList = new ArrayList<>();
        try {
            con = connectionPool.takeConnection();
            prepareStatement = con.prepareStatement(SELECT_ALL_ROOMS);
            resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomsName(resultSet.getString("NAME"));
                room.setCountry(resultSet.getString("COUNTRY"));
                room.setLightStatus(resultSet.getBoolean("LIGHT"));
                roomList.add(room);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            if (con != null) {
                connectionPool.closeConnection(con, prepareStatement, resultSet);
            }
        }
        logger.info(roomList.size());
        return roomList;
    }
}
