package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.dto.Lesson;
import org.bsuir.dto.Subject;
import org.bsuir.service.LessonService;
import org.bsuir.service.SubjectService;
import org.bsuir.service.rest.SubjectRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class UpdateSubject implements Command {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String HOURS = "hours";
    private static final String REDIRECT_URL = "/controller?command=show_subjects";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter(ID));
        String name = request.getParameter(NAME);
        Integer hours = Integer.parseInt(request.getParameter(HOURS));
        SubjectService service = new SubjectRestService();
        Optional<Subject> subjectOptional = service.getById(id);
        if (subjectOptional.isPresent()){
            Subject subject = subjectOptional.get();
            subject.setId(id);
            subject.setName(name);
            subject.setHours(hours);
            service.update(subject);
        } else {
            request.setAttribute("error", "Student with an id: "+id + "not found");
        }
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
