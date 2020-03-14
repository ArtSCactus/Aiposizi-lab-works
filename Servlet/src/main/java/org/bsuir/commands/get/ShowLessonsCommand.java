package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Group;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Subject;
import org.bsuir.dto.Teacher;
import org.bsuir.service.GroupService;
import org.bsuir.service.LessonService;
import org.bsuir.service.SubjectService;
import org.bsuir.service.TeacherService;
import org.bsuir.service.rest.GroupRestService;
import org.bsuir.service.rest.LessonRestService;
import org.bsuir.service.rest.SubjectRestService;
import org.bsuir.service.rest.TeacherRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class ShowLessonsCommand implements Command {
    private static final String PAGE_PATH = "/WEB-INF/jsp/lessons.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        LessonService service = new LessonRestService();
        List<Lesson> lessons = service.getAllLessons();
        PageContent content = new PageContent();
        TeacherService teacherService = new TeacherRestService();
        GroupService groupService = new GroupRestService();
        SubjectService subjectService = new SubjectRestService();
        List<Group> groups = groupService.getAllGroups();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Subject> subjectList =  subjectService.getAll();
        content.setAttribute("teachers", teacherList);
        content.setAttribute("groups", groups);
        content.setAttribute("subjects", subjectList);
        content.setTableContent(lessons);
        request.setAttribute("PageContent", content);
        return new CommandResult(PAGE_PATH, CommandType.GET);
    }
}
