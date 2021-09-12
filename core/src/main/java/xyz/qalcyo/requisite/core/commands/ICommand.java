package xyz.qalcyo.requisite.core.commands;

import xyz.qalcyo.requisite.core.commands.exceptions.CommandException;
import xyz.qalcyo.requisite.core.commands.exceptions.IncorrectUsageException;

import java.util.List;

public interface ICommand {
    CommandData data();
    void execute(List<String> args) throws CommandException;

    default IncorrectUsageException incorrectUsage(String usage) throws IncorrectUsageException {
        throw new IncorrectUsageException(usage);
    }
}