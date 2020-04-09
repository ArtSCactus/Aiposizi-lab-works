package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Group;
import org.bsuir.dto.Student;
import org.bsuir.service.GroupService;
import org.bsuir.service.StudentService;
import org.bsuir.service.rest.GroupRestService;
import org.bsuir.service.rest.StudentRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowStudentsCommand implements Command {
    private static final String REDIRECT_URL = "/WEB-INF/jsp/main.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        StudentService service = new StudentRestService();
        List<Student> studentList = service.getAllStudents();
        PageContent content = new PageContent(studentList);
        GroupService groupService = new GroupRestService();
        List<Group> groups = groupService.getAllGroups();
        content.setAttribute("groups", groups);
        request.setAttribute("PageContent", content);
        return new CommandResult(REDIRECT_URL, CommandType.GET);
    }
}
