package org.bsuir.commands.factory;

import org.bsuir.commands.main.CommandEnum;
import org.bsuir.commands.main.Command;
import org.bsuir.commands.main.DefaultCommand;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {
    private static final String COMMAND = "command";

    public Command defineCommand(HttpServletRequest request) {
        Command current = new DefaultCommand();
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException ex) {
            request.setAttribute("wrongAction", action + " command not found or wrong!");
        }
        return current;
    }
}
