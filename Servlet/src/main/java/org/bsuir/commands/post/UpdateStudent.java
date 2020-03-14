package org.bsuir.commands.post;

import org.bsuir.commands.main.Command;
import org.bsuir.commands.result.CommandResult;
import org.bsuir.commands.result.CommandType;
import org.bsuir.dto.Student;
import org.bsuir.service.StudentService;
import org.bsuir.service.rest.StudentRestService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ArtSCactus
 * @version 1.0
 */
public class UpdateStudent implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name= request.getParameter("name");
        String surname = request.getParameter("surname");
        String rating = request.getParameter("rating");
        String group = request.getParameter("group");
        String id = request.getParameter("id");
        Long ratingLong = Long.parseLong(rating);
        Long groupLong = Long.parseLong(group);
        Long idLong = Long.parseLong(id);
        StudentService service = new StudentRestService();
        service.update(new Student(idLong, name, surname, ratingLong, groupLong));
        return new CommandResult("/controller?command=show_students", CommandType.POST);
    }
}
