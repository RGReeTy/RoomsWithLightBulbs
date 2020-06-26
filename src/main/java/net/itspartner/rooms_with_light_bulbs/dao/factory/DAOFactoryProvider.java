package net.itspartner.rooms_with_light_bulbs.dao.factory;


import net.itspartner.rooms_with_light_bulbs.dao.factory.impl.SQLDAOFactory;

public class DAOFactoryProvider {

    private static final DAOFactory sqlDaoFactory = SQLDAOFactory.getInstance();
    private static final DAOFactoryProvider daoFactoryProvider = new DAOFactoryProvider();

    private DAOFactoryProvider() {
    }

    public static DAOFactoryProvider getInstance() {
        return daoFactoryProvider;
    }


    public static DAOFactory getSqlDaoFactory() {
        return sqlDaoFactory;
    }
}
