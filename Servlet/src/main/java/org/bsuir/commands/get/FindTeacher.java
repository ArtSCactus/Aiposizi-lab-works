package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Teacher;
import org.bsuir.service.TeacherService;
import org.bsuir.service.rest.TeacherRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class FindTeacher implements Command {
    private static final String REDIRECTION_URL = "/WEB-INF/jsp/teachers.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        TeacherService service = new TeacherRestService();
        List<Teacher> teacherList = service.getAllTeachers();
        PageContent content = new PageContent();
        content.setTableContent(teacherList);
        Optional<Teacher> teacherOptional = service.getById(id);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            content.setAttribute("foundTeacher", teacher);
        } else {
            content.setAttribute("foundTeacher", null);
        }
        request.setAttribute("PageContent", content);
        return new CommandResult(REDIRECTION_URL, CommandType.GET);
    }
}
