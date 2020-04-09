package org.bsuir.commands.main;

import org.bsuir.commands.get.*;
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
    GET_TEACHER {
        {
            this.command = new FindTeacher();
        }
    },
    ADD_TEACHER {
        {
            this.command = new AddTeacher();
        }
    },
    SHOW_LESSONS {
        {
            this.command = new ShowLessonsCommand();
        }
    },
    ADD_LESSON {
        {
            this.command = new AddLesson();
        }
    },
    DELETE_LESSON {
        {
            this.command = new DeleteLesson();
        }
    },
    GET_LESSON {
        {
            this.command = new FindLesson();
        }
    },
    UPDATE_LESSON {
        {
            this.command = new UpdateLesson();
        }
    },
    SHOW_SUBJECTS{
        {
            this.command = new ShowSubjectsCommand();
        }
    },
    ADD_SUBJECT {
        {
            this.command = new AddSubject();
        }
    },
    GET_SUBJECT {
        {
            this.command = new FindSubject();
        }
    },
    UPDATE_SUBJECT {
        {
            this.command = new UpdateSubject();
        }
    },
    DELETE_SUBJECT {
        {
            this.command = new DeleteSubject();
        }
    },
    APP{
        {
            this.command = new GetAppCommand();
        }
    };
    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
