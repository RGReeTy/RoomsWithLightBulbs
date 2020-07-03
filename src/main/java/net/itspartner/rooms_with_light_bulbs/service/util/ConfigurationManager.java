package net.itspartner.rooms_with_light_bulbs.service.util;

import java.util.ResourceBundle;


/**
 * The type Configuration manager.
 */
public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	private ConfigurationManager() {
	}

	/**
	 * Gets property.
	 *
	 * @param key the String
	 * @return the property
	 */
	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
