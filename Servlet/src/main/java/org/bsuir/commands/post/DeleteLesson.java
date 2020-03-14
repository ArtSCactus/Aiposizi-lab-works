package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.service.LessonService;
import org.bsuir.service.rest.LessonRestService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class DeleteLesson implements Command {
    private static final String LESSON_ID_PARAM = "id";
    private static final String REDIRECT_URL = "/controller?command=show_lessons";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(LESSON_ID_PARAM));
        LessonService service = new LessonRestService();
        service.removeById(id);
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
