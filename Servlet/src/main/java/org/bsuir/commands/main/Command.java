package org.bsuir.commands.main;

import org.bsuir.commands.result.CommandResult;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request);
}
