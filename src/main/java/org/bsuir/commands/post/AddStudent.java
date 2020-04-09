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
public class AddStudent implements Command {
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String RATING = "rating";
    private static final String GROUP_ID = "groupId";
    private static final String REDIRECT_URL = "/controller?command=show_students";


    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String ratingStr = request.getParameter(RATING);
        String groupIdStr = request.getParameter(GROUP_ID);
        Long ratingLong = Long.parseLong(ratingStr);
        Long groupIdLong = Long.parseLong(groupIdStr);
        StudentService service = new StudentRestService();
        Student student = new Student(null, name, surname, ratingLong, groupIdLong);
        service.update(student);
        return new CommandResult(REDIRECT_URL, CommandType.POST);
    }
}
