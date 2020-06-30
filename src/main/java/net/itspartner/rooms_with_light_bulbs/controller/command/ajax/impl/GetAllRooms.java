package net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl;

import com.google.gson.Gson;
import net.itspartner.rooms_with_light_bulbs.bean.Room;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommand;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAllRooms implements AjaxCommand {

    private final static Logger logger = Logger.getLogger(GetAllRooms.class);
    private final static String ROOMS = "roomList";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GetAllRooms starts");

        String answer = null;
        Map<String, Object> responseParams = new HashMap<>();
        Gson gson = new Gson();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        try {
            List<Room> roomList = roomService.getAllRooms();
            responseParams.put(ROOMS, roomList);
            answer = gson.toJson(responseParams);
            logger.info(roomList.toString());
        } catch (ServiceException e) {
            response.setStatus(500);
        }

        return answer;
    }

}
