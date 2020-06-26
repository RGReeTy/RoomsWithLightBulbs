package net.itspartner.rooms_with_light_bulbs.service.factory;

import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.RoomServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final RoomService roomService = new RoomServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }


    public RoomService getRoomService() {
        return roomService;
    }

}
