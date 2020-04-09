package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.*;
import org.bsuir.service.*;
import org.bsuir.service.rest.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class GetAppCommand implements Command {
    private static final String SINGLE_PAGE_PATH = "/WEB-INF/jsp/application.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        SubjectService subjectService = new SubjectRestService();
        TeacherService teacherService = new TeacherRestService();
        LessonService lessonService = new LessonRestService();
        GroupService groupService = new GroupRestService();
        StudentService studentService = new StudentRestService();
        PageContent content = new PageContent();
        List<Group> groups = groupService.getAllGroups();
        List<Lesson> lessons = lessonService.getAllLessons();
        List<Teacher> teacherList = teacherService.getAllTeachers();
        List<Subject> subjectList =  subjectService.getAll();
        List<Student> studentsList = studentService.getAllStudents();
        content.setAttribute("teachers", teacherList);
        content.setAttribute("groups", groups);
        content.setAttribute("subjects", subjectList);
        content.setAttribute("lessons", lessons);
        content.setAttribute("students", studentsList);
        request.setAttribute("PageContent", content);
        return new CommandResult(SINGLE_PAGE_PATH, CommandType.GET);
    }
}
