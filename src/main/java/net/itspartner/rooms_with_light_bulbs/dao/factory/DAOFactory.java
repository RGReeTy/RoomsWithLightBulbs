package net.itspartner.rooms_with_light_bulbs.dao.factory;

import net.itspartner.rooms_with_light_bulbs.dao.RoomDAO;

/**
 * The interface Dao factory.
 */
public interface DAOFactory {

    /**
     * Gets country dao.
     *
     * @return the country dao
     */
    RoomDAO getCountryDao();

}
