package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Student;
import org.bsuir.service.StudentService;
import org.bsuir.service.rest.StudentRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class FindStudent implements Command {
    private static final String ID_PARAM = "id";
    private static final String REDIRECT_URL = "/WEB-INF/jsp/main.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(ID_PARAM));
        StudentService service = new StudentRestService();
        List<Student> students = service.getAllStudents();
        Optional<Student> studentOptional= service.getById(id);
        PageContent content = new PageContent();
        content.setTableContent(students);

        if(studentOptional.isPresent()){
            Student student = studentOptional.get();
            content.setAttribute("foundStudent", student);
            request.setAttribute("PageContent", content);
        } else {
            content.setAttribute("foundStudent", null);
            request.setAttribute("PageContent", content);
        }
        return new CommandResult(REDIRECT_URL, CommandType.GET);
    }
}
