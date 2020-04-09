package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.service.TeacherService;
import org.bsuir.service.rest.TeacherRestService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class AddTeacher implements Command {
    private static final String REDIRECTION_URL = "/controller?command=show_teachers";
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String teacherName = request.getParameter("name");
        String teacherSurname = request.getParameter("surname");
        TeacherService service = new TeacherRestService();
        service.createTeacher(teacherName, teacherSurname);
        return new CommandResult(REDIRECTION_URL, CommandType.POST);
    }
}
