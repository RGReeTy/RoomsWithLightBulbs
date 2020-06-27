package net.itspartner.rooms_with_light_bulbs.controller.command.ajax.impl;

import com.google.gson.Gson;
import net.itspartner.rooms_with_light_bulbs.controller.command.ajax.AjaxCommand;
import net.itspartner.rooms_with_light_bulbs.service.RoomService;
import net.itspartner.rooms_with_light_bulbs.service.ServiceException;
import net.itspartner.rooms_with_light_bulbs.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AddCountry implements AjaxCommand {

    private final static String RESPONSE_PARAMETER_SET_COUNTRIES = "country";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String answer = null;
        Map<String, Object> responseParams = new HashMap<>();
        Gson gson = new Gson();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RoomService roomService = serviceFactory.getRoomService();

        try {
            Set<String> countries = roomService.getAllCountries();
            responseParams.put("countries", countries);
            answer = gson.toJson(responseParams);

        } catch (ServiceException e) {
            response.setStatus(500);
        }

        return answer;
    }

}
