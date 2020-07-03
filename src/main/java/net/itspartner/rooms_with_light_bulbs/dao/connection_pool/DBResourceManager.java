package net.itspartner.rooms_with_light_bulbs.dao.connection_pool;

import java.util.ResourceBundle;

/**
 * The type Db resource manager.
 */
public class DBResourceManager {

    private static DBResourceManager instance;
    private ResourceBundle bundle = ResourceBundle.getBundle("connection_pool");

    private DBResourceManager() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DBResourceManager getInstance() {

        if (instance == null) {
            instance = new DBResourceManager();
        }

        return instance;
    }

    /**
     * Gets value.
     *
     * @param key the String
     * @return the value
     */
    String getValue(String key) {
        return bundle.getString(key);
    }
}
