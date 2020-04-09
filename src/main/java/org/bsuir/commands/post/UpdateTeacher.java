package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.dto.Teacher;
import org.bsuir.service.TeacherService;
import org.bsuir.service.rest.TeacherRestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class UpdateTeacher implements Command {
    private static final String REDIRECTION_URL = "/controller?command=show_teachers";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        String newName = request.getParameter("name");
        String newSurname = request.getParameter("surname");
        TeacherService service = new TeacherRestService();
        Optional<Teacher> teacherOptional = service.getById(id);
        if (teacherOptional.isPresent()){
            Teacher teacher = teacherOptional.get();
            teacher.setId(id);
            teacher.setName(newName);
            teacher.setSurname(newSurname);
            service.update(teacher);
        } else {
            request.setAttribute("error", "Student with an id: "+id + "not found");
        }
        return new CommandResult(REDIRECTION_URL, CommandType.POST);
    }
}
