package org.bsuir.controller;

import org.bsuir.commands.factory.CommandFactory;
import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        LOGGER.error("Request URL: " + request.getRequestURL()
                + "\nRequest URI: " + request.getRequestURI()
                + "\nProtocol: " + request.getProtocol()
                + "\nMethod: " + request.getMethod()
                + "\nLocal addr: " + request.getLocalAddr()
                + "\nRemote addr: " + request.getRemoteAddr()
                + "\nRemote host: " + request.getRemoteHost()
                + "\nContext path: " + request.getContextPath());
        CommandResult commandResult;
        CommandFactory client = new CommandFactory();
        Command command = client.defineCommand(request);
        commandResult = command.execute(request);
        try {
            switch (commandResult.getExecutedCommandType()) {
                case POST:
                    response.sendRedirect(request.getContextPath() + commandResult.getUrl());
                    break;
                case GET:
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(commandResult.getUrl());
                    dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            LOGGER.error("An error was occurred while command executing: " + e.getMessage());
        }
    }
}
