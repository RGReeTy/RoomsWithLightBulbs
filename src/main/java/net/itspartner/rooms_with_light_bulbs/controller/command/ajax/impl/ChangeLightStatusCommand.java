package net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl;

import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommand;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The class to Change light status command.
 */
public class ChangeLightStatusCommand implements AjaxCommand {

    private static final Logger logger = Logger.getLogger(ChangeLightStatusCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String answer = "ok";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        logger.info("ChangeLightStatusCommand start");

        try {
            String roomName = request.getParameter("localRoomName");
            boolean lightStatus = Boolean.parseBoolean(request.getParameter("localLight"));

            logger.info("roomName = " + roomName +"; light = " + lightStatus);

            roomService.changeLightStatus(lightStatus, roomName.trim());

        } catch (ServiceException e) {
            logger.error(e);
            response.setStatus(500);

        }
        return answer;
    }
}