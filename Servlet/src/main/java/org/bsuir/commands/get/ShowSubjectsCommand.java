package org.bsuir.commands.get;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.commands.result.PageContent;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Subject;
import org.bsuir.service.LessonService;
import org.bsuir.service.SubjectService;
import org.bsuir.service.rest.SubjectRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class ShowSubjectsCommand implements Command {
    private static final String PAGE_PATH = "/WEB-INF/jsp/subjects.jsp";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        SubjectService service = new SubjectRestService();
        List<Subject> subjects = service.getAll();
        PageContent content = new PageContent();
        content.setTableContent(subjects);
        request.setAttribute("PageContent", content);
        return new CommandResult(PAGE_PATH, CommandType.GET);
    }
}
