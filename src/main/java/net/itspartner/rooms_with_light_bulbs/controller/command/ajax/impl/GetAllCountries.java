package net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl;

import com.google.gson.Gson;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommand;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The method to Get all countries.
 */
public class GetAllCountries implements AjaxCommand {

    private final static Logger logger = Logger.getLogger(GetAllCountries.class);
    private final static String COUNTRY = "country";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String answer = null;
        Map<String, Object> responseParams = new HashMap<>();
        Gson gson = new Gson();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        try {
            Set<String> countries = roomService.getAllCountries();
            responseParams.put(COUNTRY, countries);
            answer = gson.toJson(responseParams);
            logger.info(countries.toString());
        } catch (ServiceException e) {
            response.setStatus(500);
        }

        return answer;
    }

}
