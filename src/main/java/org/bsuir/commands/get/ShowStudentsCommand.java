package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Student;
import org.bsuir.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowStudentsCommand implements Command {
    private static final String REDIRECT_URL = "/WEB-INF/jsp/main.jsp";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        StudentService service = new StudentService();
        List<Student> studentList = service.getAllStudents();
        PageContent content = new PageContent(studentList);
        request.setAttribute("PageContent", content);
        return new CommandResult(REDIRECT_URL, CommandType.GET);
    }
}
