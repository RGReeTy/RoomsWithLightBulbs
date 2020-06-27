package net.itspartner.rooms_with_light_bulbs.controller.command.front.impl;

import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.Command;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;
import net.itspartner.rooms_with_light_bulbs.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GetAllRoomsCommand implements Command {

    private static final Logger logger = Logger.getLogger(GetAllRoomsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        try {
            List<Room> roomList = roomService.getAllRooms();
            if(roomList != null){
                request.setAttribute("roomList", roomList);
            }
            forwardToPage(request, response, ConfigurationManager.getProperty("path.page.room"));
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute("error", e.getMessage());
            response.sendRedirect(ConfigurationManager.getProperty("path.page.index"));
        }

    }
}