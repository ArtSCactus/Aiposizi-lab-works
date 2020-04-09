package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Subject;
import org.bsuir.service.SubjectService;
import org.bsuir.service.rest.SubjectRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class FindSubject implements Command {
    private static final String PAGE_PATH = "/WEB-INF/jsp/subjects.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        SubjectService service = new SubjectRestService();
        List<Subject> subjectList = service.getAll();
        PageContent content = new PageContent();
        content.setTableContent(subjectList);
        Optional<Subject> lessonOptional = service.getById(id);
        if (lessonOptional.isPresent()){
            Subject subject = lessonOptional.get();
            content.setAttribute("foundSubject", subject);
        } else {
            content.setAttribute("foundSubject", null);
        }
        request.setAttribute("PageContent", content);
        return new CommandResult(PAGE_PATH, CommandType.GET);
    }


}
