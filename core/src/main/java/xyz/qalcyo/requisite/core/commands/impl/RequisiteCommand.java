package xyz.qalcyo.requisite.core.commands.impl;

import xyz.qalcyo.requisite.core.IRequisite;
import xyz.qalcyo.requisite.core.commands.annotations.Command;

@Command(
        name = "requisite",
        generateTabCompletions = true
)
public class RequisiteCommand {

    private final IRequisite requisite;

    public RequisiteCommand(IRequisite requisite) {
        this.requisite = requisite;
    }

    @Command.Process
    public void execute() {
        requisite.openMenu();
    }

}