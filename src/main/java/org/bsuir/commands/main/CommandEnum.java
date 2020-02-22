package org.bsuir.commands.main;

import org.bsuir.commands.ShowStudentsCommand;
import org.bsuir.commands.ShowTeachersCommand;
import org.bsuir.commands.get.FindStudent;
import org.bsuir.commands.get.FindTeacher;
import org.bsuir.commands.post.*;

public enum CommandEnum {
    SHOW_STUDENTS {
        {
            this.command = new ShowStudentsCommand();
        }
    },
    SHOW_TEACHERS {
        {
            this.command = new ShowTeachersCommand();
        }
    },
    ADD_STUDENT {
        {
            this.command = new AddStudent();
        }
    },
    DELETE_STUDENT {
        {
            this.command = new DeleteStudent();
        }
    },
    GET_STUDENT {
        {
            this.command = new FindStudent();
        }
    },
    UPDATE_STUDENT {
        {
            this.command = new UpdateStudent();
        }
    },
    UPDATE_TEACHER {
        {
            this.command = new UpdateTeacher();
        }
    },
    DELETE_TEACHER {
        {
            this.command = new DeleteTeacher();
        }
    },
    GET_TEACHER{
        {
            this.command = new FindTeacher();
        }
    },
    ADD_TEACHER{
        {
            this.command = new AddTeacher();
        }
    };
    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
