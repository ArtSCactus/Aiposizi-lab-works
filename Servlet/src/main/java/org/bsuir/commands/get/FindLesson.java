package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Group;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Teacher;
import org.bsuir.service.GroupService;
import org.bsuir.service.LessonService;
import org.bsuir.service.TeacherService;
import org.bsuir.service.rest.LessonRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class FindLesson implements Command {
    private static final String REDIRECTION_URL = "/WEB-INF/jsp/lessons.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        LessonService service = new LessonRestService();
        List<Lesson> lessonList = service.getAllLessons();
        PageContent content = new PageContent();
        content.setTableContent(lessonList);
        Optional<Lesson> lessonOptional = service.getById(id);
        TeacherService teacherService = new TeacherService();
        GroupService groupService = new GroupService();
        List<Group> groups = groupService.getAllGroups();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        content.setAttribute("teachers", teacherList);
        content.setAttribute("groups", groups);
        if (lessonOptional.isPresent()){
            Lesson lesson = lessonOptional.get();
            content.setAttribute("foundLesson", lesson);
        } else {
            content.setAttribute("foundLesson", null);
        }
        request.setAttribute("PageContent", content);
        return new CommandResult(REDIRECTION_URL, CommandType.GET);
    }
}
