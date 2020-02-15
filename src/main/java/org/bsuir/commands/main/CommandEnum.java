package org.bsuir.commands.main;

import org.bsuir.commands.ShowStudentsCommand;
import org.bsuir.commands.ShowTeachersCommand;
import org.bsuir.commands.get.FindStudent;
import org.bsuir.commands.post.AddStudentCommand;
import org.bsuir.commands.post.DeleteStudent;
import org.bsuir.commands.post.UpdateStudent;

public enum CommandEnum {
    SHOW_STUDENTS{
        {
            this.command = new ShowStudentsCommand();
        }
    },
    SHOW_TEACHERS{
        {
            this.command = new ShowTeachersCommand();
        }
    },
    ADD_STUDENT{
        {
            this.command = new AddStudentCommand();
        }
    },
    DELETE_STUDENT{
        {
            this.command = new DeleteStudent();
        }
    },
    GET_STUDENT{
        {
            this.command = new FindStudent();
        }
    },
    UPDATE_STUDENT{
        {
            this.command = new UpdateStudent();
        }
    };
    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
