package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.service.StudentService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class DeleteStudent implements Command {
    private static final String ID_PARAM = "id";
    private static final String REDIRECT_URL = "/controller?command=show_students";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(ID_PARAM));
        StudentService service = new StudentService();
        service.delete(id);
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
