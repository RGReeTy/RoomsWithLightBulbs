package net.itspartner.rooms_with_light_bulbs.dao.factory.impl;

import net.itspartner.rooms_with_light_bulbs.dao.RoomDAO;
import net.itspartner.rooms_with_light_bulbs.dao.RoomDAOImpl;
import net.itspartner.rooms_with_light_bulbs.dao.factory.DAOFactory;

/**
 * The type Sqldao factory.
 */
public class SQLDAOFactory implements DAOFactory {

    private final static SQLDAOFactory sqlDAOFactory = new SQLDAOFactory();
    private final static RoomDAO COUNTRY_DAO = new RoomDAOImpl();


    private SQLDAOFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SQLDAOFactory getInstance() {
        return sqlDAOFactory;
    }


    @Override
    public RoomDAO getCountryDao() {
        return COUNTRY_DAO;
    }
}
