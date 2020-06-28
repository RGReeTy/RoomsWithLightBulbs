package net.itspartner.rooms_with_light_bulbs.controller.command.front.impl;

import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.Command;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;
import net.itspartner.rooms_with_light_bulbs.service.util.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddNewRoomCommand implements Command {

    private final static Logger logger = Logger.getLogger(AddNewRoomCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        try {
            String name = request.getParameter("name");
            String id_country = request.getParameter("id_country");
            logger.info("AddNewRoomCommand: name=" + name + ", id_country=" + id_country);
            System.out.println("AddNewRoomCommand: name=" + name + ", id_country=" + id_country);

            Room room = new Room();
            room.setRoomsName(name);
            room.setCountry(id_country);
            room.setLightStatus(false);
            if (roomService.checkIsRoomNameFree(name)) {
                roomService.addNewRoom(room);
            } else {
                request.setAttribute("error", "The room '" + name + "' is already exist!");
            }
            forwardToPage(request, response, request.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute("error", e.getMessage());
            response.sendRedirect(ConfigurationManager.getProperty("path.page.index"));
        }

    }
}