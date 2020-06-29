package net.itspartner.rooms_with_light_bulbs.controller;

import net.itspartner.rooms_with_light_bulbs.controller.command.CommandProvider;
import net.itspartner.rooms_with_light_bulbs.controller.command.front.Command;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/Controller", name = "FrontController")
@MultipartConfig
public class FrontController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getFrontCommand(commandName);
        command.execute(request, response);
    }
}