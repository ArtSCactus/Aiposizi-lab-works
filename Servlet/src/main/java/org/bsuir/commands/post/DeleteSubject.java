package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.service.SubjectService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class DeleteSubject implements Command {
    private static final String ID_PARAM = "id";
    private static final String REDIRECT_URL = "/controller?command=show_subjects";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(ID_PARAM));
        SubjectService service = new SubjectService();
        service.removeById(id);
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
