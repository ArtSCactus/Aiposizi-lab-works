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
        LessonService service = new LessonService();
        List<Lesson> lessons = service.getAllLessons();
        PageContent content = new PageContent();
        TeacherService teacherService = new TeacherService();
        GroupService groupService = new GroupService();
        List<Group> groups = groupService.getAllGroups();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        content.setAttribute("teachers", teacherList);
        content.setAttribute("groups", groups);
        content.setTableContent(lessons);
        request.setAttribute("PageContent", content);
        return new CommandResult(PAGE_PATH, CommandType.GET);
    }
}
