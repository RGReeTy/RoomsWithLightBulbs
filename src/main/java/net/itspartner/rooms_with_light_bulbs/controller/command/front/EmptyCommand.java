package net.itspartner.rooms_with_light_bulbs.controller.command.front;


import net.itspartner.rooms_with_light_bulbs.service.util.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmptyCommand implements Command {

    /**
     * EmptyCommand will be executed if a servlet has been accessed without a command.
     *
     * @param request HttpServletRequest request
     *
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        forwardToPage(request, response, ConfigurationManager.getProperty("path.page.index"));
    }
}