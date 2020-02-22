package org.bsuir.commands.main;

import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;

import javax.servlet.http.HttpServletRequest;

/**Default command, that uses in case of undefined command.
 *
 */
public class DefaultCommand implements Command {
    private static final String MAIN_PAGE_PATH = "/WEB-INF/jsp/main.jsp";

    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(MAIN_PAGE_PATH, CommandType.GET);
    }
}
