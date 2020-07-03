package net.itspartner.rooms_with_light_bulbs.service.factory;

import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.RoomServiceImpl;

/**
 * The type Service factory.
 */
public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final RoomService roomService = new RoomServiceImpl();

    private ServiceFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceFactory getInstance() {
        return instance;
    }


    /**
     * Gets room service.
     *
     * @return the room service
     */
    public RoomService getRoomService() {
        return roomService;
    }

}
