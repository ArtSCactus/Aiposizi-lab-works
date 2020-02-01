package org.bsuir.commands.main;

import org.bsuir.commands.ShowStudentsCommand;

public enum CommandEnum {
    SHOW_STUDENTS{
        {
            this.command = new ShowStudentsCommand();
        }
    };
    Command command;

    public Command getCurrentCommand() {
        return command;
    }

}
