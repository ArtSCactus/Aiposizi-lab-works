package org.bsuir.commands.main;

import org.bsuir.commands.ShowStudentsCommand;
import org.bsuir.commands.ShowTeachersCommand;

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
    };
    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
