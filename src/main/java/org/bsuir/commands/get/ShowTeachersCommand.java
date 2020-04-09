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

public class ShowTeachersCommand implements Command {
    private static final String TEACHERS_PAGE_PATH ="/WEB-INF/jsp/teachers.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        TeacherService service = new TeacherRestService();
        List<Teacher> teachers = service.getAllTeachers();
        request.setAttribute("PageContent", new PageContent(teachers));
        return new CommandResult(TEACHERS_PAGE_PATH, CommandType.GET);
    }
}
