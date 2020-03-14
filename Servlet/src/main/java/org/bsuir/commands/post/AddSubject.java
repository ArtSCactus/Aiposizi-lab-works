package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Subject;
import org.bsuir.service.LessonService;
import org.bsuir.service.SubjectService;
import org.bsuir.service.rest.SubjectRestService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class AddSubject implements Command {
    private static final String SUBJECT_NAME_PARAM = "name";
    private static final String HOURS_PARAM = "hours";
    private static final String REDIRECT_URL = "/controller?command=show_subjects";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(SUBJECT_NAME_PARAM);
        Integer hours = Integer.parseInt(request.getParameter(HOURS_PARAM));
        SubjectService service = new SubjectRestService();
        service.update(new Subject(null, name, hours));
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
