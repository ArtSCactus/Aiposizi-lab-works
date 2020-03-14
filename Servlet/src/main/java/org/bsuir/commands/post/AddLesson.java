package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.dto.Lesson;
import org.bsuir.service.LessonService;
import org.bsuir.service.rest.LessonRestService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class AddLesson implements Command {
    private static final String TEACHER_ID_PARAM = "teacherId";
    private static final String GROUP_ID_PARAM = "groupId";
    private static final String SUBJECT_ID_PARAM = "subjectId";
    private static final String REDIRECT_URL = "/controller?command=show_lessons";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long teacherId = Long.parseLong(request.getParameter(TEACHER_ID_PARAM));
        Long groupId = Long.parseLong(request.getParameter(GROUP_ID_PARAM));
        Long subjectId = Long.parseLong(request.getParameter(SUBJECT_ID_PARAM));
        LessonService service = new LessonRestService();
        service.update(new Lesson(null, groupId, subjectId, teacherId));
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
